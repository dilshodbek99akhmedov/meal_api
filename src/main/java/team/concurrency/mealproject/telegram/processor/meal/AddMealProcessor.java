package team.concurrency.mealproject.telegram.processor.meal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import team.concurrency.mealproject.dto.meal.MealCreateDto;
import team.concurrency.mealproject.service.meal.MealService;
import team.concurrency.mealproject.telegram.MealManagementBot;
import team.concurrency.mealproject.telegram.button.InlineBoard;
import team.concurrency.mealproject.telegram.config.EntityHolder;
import team.concurrency.mealproject.telegram.config.State;
import team.concurrency.mealproject.telegram.state.AddMealState;
import team.concurrency.mealproject.telegram.state.MenuState;

@Component
@RequiredArgsConstructor
public class AddMealProcessor {
    private final State state;
    private final MealManagementBot BOT;
    private final EntityHolder entityHolder;
    private final InlineBoard inlineBoard;
    private final MealService mealService;


    public void process(Message message, AddMealState addMealState) {
        String chatId = message.getChatId().toString();


        if (AddMealState.NAME.equals(addMealState)) {
            String name = message.getText();

            MealCreateDto mealCreateDto = entityHolder.getMealCD(chatId);
            mealCreateDto.setName(name);
            entityHolder.setMealCD(chatId, mealCreateDto);

            SendMessage sendMessage =
                    new SendMessage(chatId, "Enter description about meal:");
            BOT.executeMessage(sendMessage);

            state.setAddMealState(chatId, AddMealState.DESCRIPTION);
        } else if (AddMealState.DESCRIPTION.equals(addMealState)) {
            String description = message.getText();

            MealCreateDto mealCreateDto = entityHolder.getMealCD(chatId);
            mealCreateDto.setDescription(description);
            entityHolder.setMealCD(chatId, mealCreateDto);

            SendMessage sendMessage =
                    new SendMessage(chatId, "Send photo of meal:");
            BOT.executeMessage(sendMessage);

            state.setAddMealState(chatId, AddMealState.PHOTO);
        } else if (AddMealState.PHOTO.equals(addMealState)) {

            if (!message.hasPhoto()) {
                DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
                BOT.executeMessage(deleteMessage);
            } else {
                String fileId = message.getMediaGroupId();

                MealCreateDto mealCreateDto = entityHolder.getMealCD(chatId);
                mealCreateDto.setPath(fileId);

                mealService.create(mealCreateDto);
                entityHolder.removeMealCD(chatId);

                SendMessage sendMessage = new SendMessage(chatId, "Saved Meal");
                BOT.executeMessage(sendMessage);
                state.setMenuState(chatId, MenuState.UNDEFINED);
            }
        }


    }


}
