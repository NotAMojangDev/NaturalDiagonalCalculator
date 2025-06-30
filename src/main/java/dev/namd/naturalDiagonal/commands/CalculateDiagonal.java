package dev.namd.naturalDiagonal.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@CommandAlias("calculate-natural-diagonal|cnd|dn")
public class CalculateDiagonal extends BaseCommand {
    @Default
    public void onCommand(CommandSender sender, @NotNull String @NotNull [] args) {
        if (args.length < 2) {
            sender.sendMessage("§cUsage: /dn <num> <num>");
            return;
        }

        double length;
        double width;

        try {
            length = Integer.parseInt(args[0]);
            width = Integer.parseInt(args[1]);
            if (length < width) {
                width = length;
                length = Integer.parseInt(args[1]);
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("§cIncorrect Number Format!");
            return;
        }

        if (length <= 1 || width <= 1) {
            sender.sendMessage("§cPlease provide numbers bigger than 1");
            return;
        }

        double avgLength = length / width;
        long rounded = Math.round(avgLength);
        double remainder = length % width;

        if (remainder == 0) {
            sender.sendMessage(((int) width) + " segments of " + rounded + " blocks");
            return;
        }

        double amountRemainder = width - remainder;
        if (avgLength < rounded) { // Rounded up
            sender.sendMessage(((int) remainder) + " segment" + ((remainder == 1) ? "" : "s") + " of " +  ((int) rounded) + " block" + ((rounded == 1) ? "" : "s") + " and " +
                    ((int) amountRemainder) + " segment" + ((amountRemainder == 1) ? "" : "s") + " of " + ((int) rounded - 1) + " block" + ((rounded - 1 == 1) ? "" : "s"));
        } else { // Rounded Down

            if ((remainder * (rounded - 1) + amountRemainder * rounded) == length)
                sender.sendMessage(((int) remainder) + " segment" + ((remainder == 1) ? "" : "s") + " of " +  ((int) rounded - 1) + " block" + ((rounded - 1 == 1) ? "" : "s") + " and " +
                    ((int) amountRemainder) + " segment" + ((amountRemainder == 1) ? "" : "s") + " of " + ((int) rounded) + " block" + ((rounded == 1) ? "" : "s"));
            else
                sender.sendMessage(((int) remainder) + " segment" + ((remainder == 1) ? "" : "s") + " of " +  ((int) rounded + 1) + " block" + ((rounded + 1 == 1) ? "" : "s") + " and " +
                    ((int) amountRemainder) + " segment" + ((amountRemainder == 1) ? "" : "s") + " of " + ((int) rounded) + " block" + ((rounded == 1) ? "" : "s"));
        }
    }
}
