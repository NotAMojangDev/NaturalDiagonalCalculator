package dev.namd.naturalDiagonal;

import co.aikar.commands.BukkitCommandManager;
import dev.namd.naturalDiagonal.commands.CalculateDiagonal;
import org.bukkit.plugin.java.JavaPlugin;

public final class NaturalDiagonal extends JavaPlugin {

    @Override
    public void onEnable() {
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.registerCommand(new CalculateDiagonal());
    }
}
