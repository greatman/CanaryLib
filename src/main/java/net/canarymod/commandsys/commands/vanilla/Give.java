package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.commandsys.CanaryCommandPermissions.GIVE$OTHER;

/**
 * Give command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Give extends VanillaCommandWrapper {
    // give <player> <item> [amount] [data] [dataTag] | alias i item

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (isNotSelfOrServer(caller, parameters[0]) && !caller.hasPermission(GIVE$OTHER)) {
            caller.notice(Translator.nativeTranslate("commands.generic.permission"));
            return;
        }
        passOn(caller, "give", parameters);
    }
}
