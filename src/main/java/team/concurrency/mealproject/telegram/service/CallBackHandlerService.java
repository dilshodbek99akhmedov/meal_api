package team.concurrency.mealproject.telegram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import team.concurrency.mealproject.dto.user.UserCreateDto;
import team.concurrency.mealproject.entity.User;
import team.concurrency.mealproject.enums.Position;
import team.concurrency.mealproject.enums.Role;
import team.concurrency.mealproject.service.auth.AuthUserService;
import team.concurrency.mealproject.telegram.MealManagementBot;
import team.concurrency.mealproject.telegram.button.InlineBoard;
import team.concurrency.mealproject.telegram.config.EntityHolder;
import team.concurrency.mealproject.telegram.config.LangConfig;
import team.concurrency.mealproject.telegram.config.State;
import team.concurrency.mealproject.telegram.processor.menu.MenuProcessor;
import team.concurrency.mealproject.telegram.state.UserState;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CallBackHandlerService {
    private final AuthUserService authUserService;
    private final MenuProcessor menuProcessor;
    private final InlineBoard inlineBoard;
    private final EntityHolder userHolder;
    private final MealManagementBot BOT;
    private final State state;


    public void languageMessage(Message message, String data) {
        String chatId = message.getChatId().toString();
        User user = authUserService.getByChatId(chatId);

        if (Objects.isNull(user)) {
            SendMessage sendMessage =
                    new SendMessage(chatId, LangConfig.get(chatId, "enter.full.name"));
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            state.setUserState(chatId, UserState.FULL_NAME);
        }
    }

    public void departmentMessage(Message message, String data) {
        String chatId = message.getChatId().toString();
        User user = authUserService.getByChatId(chatId);
        if (Objects.isNull(user)) {
            userHolder.getUserCD(chatId).setDepartment(data);
            SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "enter.position"));
            sendMessage.setReplyMarkup(inlineBoard.positionButtons());
            BOT.executeMessage(sendMessage);
            state.setUserState(chatId, UserState.POSITION);
        }
    }

    public void positionMessage(Message message, String data) {
        String chatId = message.getChatId().toString();
        User user = authUserService.getByChatId(chatId);
        if (Objects.isNull(user)) {
            userHolder.getUserCD(chatId).setPosition(data);
            SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "enter.gender"));
            sendMessage.setReplyMarkup(inlineBoard.gender(chatId));
            BOT.executeMessage(sendMessage);
            state.setUserState(chatId, UserState.GENDER);
        }
    }

    public void genderMessage(Message message, String data) {
        String chatId = message.getChatId().toString();
        User user = authUserService.getByChatId(chatId);
        if (Objects.isNull(user)) {
            userHolder.getUserCD(chatId).setGender(data);
            userHolder.getUserCD(chatId).setRole(Role.USER.getName());
            UserCreateDto createDto = userHolder.getUserCD(chatId);

            SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "sent"));
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);

            String acceptor;
            if (Position.ADMINISTRATOR.getName().equals(createDto.getPosition())) {
                acceptor = authUserService.getByRole(Role.SUPER_ADMIN.toString()).getChatId().toString();
            } else {
                acceptor = authUserService.getAdministratorOfDepartment(createDto.getDepartment()).getChatId().toString();
            }

            SendMessage toAcceptor = new SendMessage();
            toAcceptor.setChatId(acceptor);
            toAcceptor.setText("Do you have worker" +
                    "\nIn Position:" + createDto.getPosition() +
                    "\nWith Name: " + createDto.getFullName());
            toAcceptor.setReplyMarkup(inlineBoard.accept(chatId));
            BOT.executeMessage(toAcceptor);

            authUserService.create(createDto);
            userHolder.removeUserCD(chatId);

        }
    }


    public void acceptMessage(Message message, String data) {
        SendMessage toAcceptor = new SendMessage();
        toAcceptor.setChatId(message.getChatId().toString());

        SendMessage toUser = new SendMessage();
        String target = data.substring(8);

        if (data.startsWith("accept")) {
            authUserService.acceptUser(target);
            state.setUserState(target, UserState.AUTHORIZED);
            toUser.setChatId(target);
            toUser.setText("You have accepted");
            menuProcessor.sendWelcomeToUser(target);
            toAcceptor.setText("Accepted");
        } else {
            toUser.setChatId(target);
            toUser.setText("You have been rejected");
            toAcceptor.setText("Rejected");
        }

        BOT.executeMessage(toAcceptor);
        BOT.executeMessage(toUser);
    }
}
