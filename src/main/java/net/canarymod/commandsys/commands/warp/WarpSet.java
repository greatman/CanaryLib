package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;

import static net.canarymod.commandsys.CanaryCommandPermissions.*;

/**
 * Command to set a warp (including private and group warps)
 *
 * @author Chris (damagefilter)
 */
public class WarpSet implements NativeCommand {
    public void execute(MessageReceiver caller, String[] args) {
        if (caller instanceof Server || caller instanceof CommandBlock) {
            caller.notice(Translator.translate("setwarp console"));
        }
        else {
            Player player = (Player)caller;
            if (Canary.warps().warpExists(args[0]) && !player.hasPermission(WARP$SET$ADMIN)) {
                player.notice(Translator.translate("setwarp failed"));
                return;
            }
            // SET PUBLIC WARP
            if (args.length == 2) {
                Warp newWarp = new Warp(player.getLocation(), args[0]);

                Canary.warps().addWarp(newWarp);
                player.message(Colors.YELLOW + Translator.translateAndFormat("setwarp success", args[0]));
            }
            else if (args.length > 3) {
                // SET GROUP SPECIFIC WARP
                if (args[2].equalsIgnoreCase("G") && player.hasPermission(WARP$SET$GROUP)) {
                    Group[] groups = new Group[args.length - 2];

                    for (int i = 0; i < groups.length; i++) {
                        groups[i] = Canary.usersAndGroups().getGroup(args[i + 2]);
                    }
                    Warp newWarp = new Warp(player.getLocation(), groups, args[0]);

                    Canary.warps().addWarp(newWarp);
                    player.message(Colors.YELLOW + Translator.translateAndFormat("setwarp success group", args[0]));
                    return;
                }
                // SET PRIVATE WARP
                if (args[0].equalsIgnoreCase("P") && player.hasPermission(WARP$SET$PRIVATE)) {
                    Warp newWarp = new Warp(player.getLocation(), args[0], args[2], false);

                    Canary.warps().addWarp(newWarp);
                    player.message(Colors.YELLOW + Translator.translateAndFormat("setwarp success private", args[0]));
                }
                else {
                    Canary.help().getHelp(player, "setwarp");
                }
            }
            else {
                Canary.help().getHelp(player, "setwarp");
            }
        }
    }
}
