package team.concurrency.mealproject.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UpdateHandler {
    private final MessageHandler massageHandler;
    private final CallbackQueryHandler callbackQueryHandler;

    public void handle(Update update) {
        if (update.hasMessage())
            massageHandler.handle(update.getMessage());
        else if (update.hasCallbackQuery())
            callbackQueryHandler.handle(update.getCallbackQuery());
    }
}
