package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to change the prefix of a group
 *
 * @author Chris (damagefilter)
 */
public class GroupPrefix implements NativeCommand {
    // group) prefix <group> <prefix>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod prefix");
            return;
        }
        Group group = Canary.usersAndGroups().getGroup(args[0]);
        if (group == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[0]));
            return;
        }
        String prefix = null;
        if (args.length >= 2) {
            prefix = StringUtils.joinString(args, " ", 1).replaceAll("&([A-FK-Oa-fk-oRr0-9])", "\u00A7$1");
        }
        group.setPrefix(prefix);
        Canary.usersAndGroups().updateGroup(group, false);
        caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
    }
}
