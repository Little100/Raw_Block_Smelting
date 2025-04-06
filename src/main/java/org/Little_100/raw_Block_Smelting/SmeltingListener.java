package org.Little_100.raw_Block_Smelting;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

public class SmeltingListener implements Listener {

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
        ItemStack source = event.getSource();
        Material sourceType = source.getType();
        ItemStack result = null;

        // 检查是否为粗铁块
        if (sourceType == Material.RAW_IRON_BLOCK) {
            result = new ItemStack(Material.IRON_BLOCK, 1);
        } 
        // 检查是否为粗金块
        else if (sourceType == Material.RAW_GOLD_BLOCK) {
            result = new ItemStack(Material.GOLD_BLOCK, 1);
        } 
        // 检查是否为粗铜块 (1.17+)
        else if (sourceType == Material.RAW_COPPER_BLOCK) {
            // 确保服务器版本支持铜块
            try {
                 Material copperBlock = Material.COPPER_BLOCK;
                 result = new ItemStack(copperBlock, 1);
            } catch (NoSuchFieldError e) {
                // 服务器版本 < 1.17，忽略铜块
            }
        }

        // 如果找到了匹配的粗金属块，设置烧炼结果
        if (result != null) {
            event.setResult(result);
        }
    }
} 