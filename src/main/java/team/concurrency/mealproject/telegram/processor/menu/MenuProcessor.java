package team.concurrency.mealproject.telegram.processor.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import team.concurrency.mealproject.telegram.MealManagementBot;
import team.concurrency.mealproject.telegram.button.MarkupBoard;
import team.concurrency.mealproject.telegram.config.State;
import team.concurrency.mealproject.telegram.state.MenuState;

@Component
@RequiredArgsConstructor
public class MenuProcessor {
    private final MealManagementBot BOT;
    private final MarkupBoard markupBoard;
    private final State state;


    public void userMenuProcess(Message message, MenuState menuState) {
        String chatId = message.getChatId().toString();
        String text = message.getText();
        sendWelcomeToUser(chatId);

    }

    public void managerProcess(Message message, MenuState menuState) {
        String chatId = message.getChatId().toString();
        String text = message.getText();
        sendWelcomeToManager(chatId);
        if ("/meal".equals(text)) {
            state.setMenuState(chatId,MenuState.MEAL);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("MEA:");
            sendMessage.setReplyMarkup(markupBoard.mealCrud(chatId));
            BOT.executeMessage(sendMessage);
        } else if ("/menu".equals(text)) {

        } else if ("/orders".equals(text)) {

        } else if ("/feedbacks".equals(text)) {

        }

    }

    public void superMenuProcess(Message message, MenuState menuState) {
        String chatId = message.getChatId().toString();
        String text = message.getText();
        sendWelcomeToSuper(chatId);


    }

    public void adminMenuProcess(Message message, MenuState menuState) {
        String chatId = message.getChatId().toString();
        String text = message.getText();
        sendWelcomeToAdmin(chatId);

    }

    public void sendWelcomeToUser(String chatId) {
        SendMessage welcome = new SendMessage();
        welcome.setChatId(chatId);
        welcome.setText("""
                     👋Welcome
                  Our Options:   
                /menu     -> Daily Menu
                /order    -> Order Meal
                /myorder  -> Show Your order
                /feedback -> Giving Feedback to chef
                /settings -> Edit Profile""");
        BOT.executeMessage(welcome);
    }

    public void sendWelcomeToManager(String chatId) {
        SendMessage welcome = new SendMessage();
        welcome.setChatId(chatId);
        welcome.setText("""
                     👋Welcome  
                /meal      -> Meal
                /menu      -> Daily Meal
                /orders    -> Show Orders
                /feedbacks -> Show Feedbacks
                /profile   -> Profile
                /settings  -> Settings""");
        BOT.executeMessage(welcome);
    }

    public void sendWelcomeToAdmin(String chatId) {
        SendMessage welcome = new SendMessage();
        welcome.setChatId(chatId);
        welcome.setText("""
                     👋Welcome 
                /menu     -> Daily Menu
                /order    -> Order Meal
                /myorder  -> Show Your order
                /feedback -> Giving Feedback to chef
                /settings -> Edit Profile""");
        BOT.executeMessage(welcome);
    }

    public void sendWelcomeToSuper(String chatId) {
        SendMessage welcome = new SendMessage();
        welcome.setChatId(chatId);
        welcome.setText("""
                     👋Welcome
                /menu     -> Daily Menu
                /order    -> Order Meal
                /myorder  -> Show Your order
                /feedback -> Giving Feedback to chef
                /settings -> Edit Profile""");
        BOT.executeMessage(welcome);
    }
}
