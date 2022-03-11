package team.concurrency.mealproject.telegram.processor.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import team.concurrency.mealproject.dto.user.UserCreateDto;
import team.concurrency.mealproject.service.auth.AuthUserService;
import team.concurrency.mealproject.telegram.MealManagementBot;
import team.concurrency.mealproject.telegram.button.InlineBoard;
import team.concurrency.mealproject.telegram.button.MarkupBoard;
import team.concurrency.mealproject.telegram.config.EntityHolder;
import team.concurrency.mealproject.telegram.config.LangConfig;
import team.concurrency.mealproject.telegram.config.State;
import team.concurrency.mealproject.telegram.state.UserState;
import team.concurrency.mealproject.telegram.tools.Emoji;

import java.util.Objects;

@Component
public class AuthorizationProcessor {
    private final MealManagementBot BOT;
    private final EntityHolder userHolder;
    private final InlineBoard inlineBoard;
    private final State state;
    private final AuthUserService authUserService;

    @Autowired(required = false)
    public AuthorizationProcessor(MealManagementBot bot,
                                  EntityHolder userHolder,
                                  InlineBoard inlineBoard,
                                  State state,
                                  AuthUserService authUserService) {
        BOT = bot;
        this.userHolder = userHolder;
        this.inlineBoard = inlineBoard;
        this.state = state;
        this.authUserService = authUserService;
    }

    public void process(Message message, UserState userState) {
        String chatId = message.getChatId().toString();

        if (Objects.isNull(userHolder.getUserCD(chatId).getChatId())) {
            UserCreateDto createDto = userHolder.getUserCD(chatId);
            createDto.setChatId(chatId);
            userHolder.setUserCD(chatId, createDto);
        }
        //Lang
        if (UserState.LANG.equals(userState) || Objects.isNull(userState)) {
            String text = """
                    Tilni tanlang:
                    Выберите язык:
                    Choose your language:""";
            SendMessage sendMessage = new SendMessage(chatId, text);
            sendMessage.setReplyMarkup(inlineBoard.languageButtons());
            BOT.executeMessage(sendMessage);
            state.setUserState(chatId, UserState.DELETE_ALL);
        }
        //Name
        else if (UserState.FULL_NAME.equals(userState)) {
            String text = message.getText();
            if (StringUtils.isNumeric(text) || !text.equals(StringUtils.capitalize(text))) {
                SendMessage sendMessage = new SendMessage(chatId,
                        Emoji.LOOK + " " + LangConfig.get(chatId, "full.name.correctly") + "\n"
                                + LangConfig.get(chatId, "without.numbers"));
                sendMessage.setReplyMarkup(new ForceReplyKeyboard());
                BOT.executeMessage(sendMessage);
            } else {
                UserCreateDto dto = userHolder.getUserCD(chatId);
                dto.setFullName(text);
                userHolder.setUserCD(chatId, dto);
                SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "enter.username"));
//                sendMessage.setReplyMarkup(new ForceReplyKeyboard());
                BOT.executeMessage(sendMessage);
                state.setUserState(chatId, UserState.USERNAME);
            }
        }
        //username
        else if (UserState.USERNAME.equals(userState)) {
            String text = message.getText();
            UserCreateDto dto = userHolder.getUserCD(chatId);
            dto.setUsername(text);
            userHolder.setUserCD(chatId, dto);
            SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "enter.password"));
//                sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            state.setUserState(chatId, UserState.PASSWORD);
        }
        //password
        else if (UserState.PASSWORD.equals(userState)) {
            String password = message.getText();
            //todo checking password for strong
            UserCreateDto dto = userHolder.getUserCD(chatId);
            dto.setPassword(password);
            userHolder.setUserCD(chatId, dto);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(LangConfig.get(chatId, "share.phone.number"));
            sendMessage.setReplyMarkup(MarkupBoard.sharePhoneNumber(chatId));
            BOT.executeMessage(sendMessage);
            state.setUserState(chatId, UserState.PHONE_NUMBER);
        }
        //phonenumber
        else if (UserState.PHONE_NUMBER.equals(userState)) {
            if (message.hasContact()) {
                String phoneNumber = message.getContact().getPhoneNumber();
                userHolder.getUserCD(chatId).setPhoneNumber(phoneNumber);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Department:");
                sendMessage.setReplyMarkup(inlineBoard.departmentButtons());
                state.setUserState(chatId, UserState.DEPARTMENT);
                BOT.executeMessage(sendMessage);
            } else {
                SendMessage sendMessage = new SendMessage(chatId,
                        Emoji.REMOVE + " " + LangConfig.get(chatId, "invalid.number") + "\n" +
                                LangConfig.get(chatId, "correct.number"));
                BOT.executeMessage(sendMessage);
            }
        }
        //position
        else if (UserState.AUTHORIZED.equals(userState)) {
            authUserService.create(userHolder.getUserCD(chatId));
            userHolder.removeUserCD(chatId);
        }

        if (UserState.DELETE_ALL.equals(userState)) {
            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            BOT.executeMessage(deleteMessage);
        }


    }
}
