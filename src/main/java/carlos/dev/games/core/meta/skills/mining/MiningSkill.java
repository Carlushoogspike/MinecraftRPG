package carlos.dev.games.core.meta.skills.mining;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.mechanic.MechanicDoubleEventImpl;
import carlos.dev.games.core.meta.resource.SourceManager;
import carlos.dev.games.core.meta.resource.impl.BlockSource;
import carlos.dev.games.core.meta.resource.triggers.BlockTriggers;
import carlos.dev.games.core.meta.resource.values.BlockValues;
import carlos.dev.games.core.meta.skills.Skills;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.player.skill.PlayerSkill;
import carlos.dev.games.core.player.skill.PlayerSkillUtility;
import carlos.dev.games.api.skill.SkillGainXpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class MiningSkill extends MechanicDoubleEventImpl<BlockBreakEvent, BlockPlaceEvent> {

    public MiningSkill(Plugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);
        PlayerSkill playerSkill = PlayerSkillUtility.getData(player);

        Block block = event.getBlock();
        Material blockType = block.getType();

        SourceManager manager = MinecraftRPG.getInstance().getSourceManager();
        Map<?, ?> map = manager.retrieveData(BlockSource.class);
        map.forEach((t, v) -> {
            if (t instanceof BlockTriggers bt && v instanceof BlockValues vl){
                if (!bt.getIdentifier().equals("break")) return;

                String type = vl.getType().toUpperCase();
                Material valueType = Material.getMaterial(type);
                if (blockType != valueType) return;

                int playerLevel = rpgPlayer.getLevel();
                int blockLevel = vl.getRequiredLevel();

                player.sendMessage(playerLevel + " " + blockLevel);

                if (playerLevel > blockLevel)  {
                    player.sendMessage("§cVocê não tem niveis suficiente para quebrar esse bloco.");
                    event.setCancelled(true);
                    return;
                }

                double xp = vl.getXp();
                //playerSkill.addExperience(Skills.MINING, xp);
                Bukkit.getPluginManager().callEvent(new SkillGainXpEvent(Skills.MINING, playerSkill, xp));
            }
        });
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);

        Block block = event.getBlock();
        Material blockType = block.getType();

        SourceManager manager = MinecraftRPG.getInstance().getSourceManager();
        Map<?, ?> map = manager.retrieveData(BlockSource.class);
        map.forEach((t, v) -> {
            if (t instanceof BlockTriggers bt && v instanceof BlockValues vl){
                if (!bt.getIdentifier().equals("place")) return;

                String type = vl.getType().toUpperCase();
                Material valueType = Material.getMaterial(type);
                if (blockType != valueType) return;

                int playerLevel = rpgPlayer.getLevel();
                int blockLevel = vl.getRequiredLevel();

                player.sendMessage(playerLevel + " " + blockLevel);

                if (playerLevel > blockLevel)  {
                    player.sendMessage("§cVocê não tem niveis suficiente para colocar esse bloco.");
                    event.setCancelled(true);
                    return;
                }
            }
        });
    }


}
