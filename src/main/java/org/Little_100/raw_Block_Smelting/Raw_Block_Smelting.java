package org.Little_100.raw_Block_Smelting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Raw_Block_Smelting extends JavaPlugin {

    @Override
    public void onEnable() {
        // 移除旧的监听器注册 (如果存在)
        // getServer().getPluginManager().registerEvents(new SmeltingListener(), this);

        getLogger().info("正在注册粗金属块熔炼配方...");

        // 注册粗铁块配方
        addSmeltingRecipe("raw_iron_block_smelting", Material.RAW_IRON_BLOCK, Material.IRON_BLOCK);
        addBlastingRecipe("raw_iron_block_blasting", Material.RAW_IRON_BLOCK, Material.IRON_BLOCK);

        // 注册粗金块配方
        addSmeltingRecipe("raw_gold_block_smelting", Material.RAW_GOLD_BLOCK, Material.GOLD_BLOCK);
        addBlastingRecipe("raw_gold_block_blasting", Material.RAW_GOLD_BLOCK, Material.GOLD_BLOCK);

        // 注册粗铜块配方 (兼容 1.17+)
        try {
            Material rawCopperBlock = Material.RAW_COPPER_BLOCK;
            Material copperBlock = Material.COPPER_BLOCK;
            addSmeltingRecipe("raw_copper_block_smelting", rawCopperBlock, copperBlock);
            addBlastingRecipe("raw_copper_block_blasting", rawCopperBlock, copperBlock);
            getLogger().info("已注册粗铜块配方。");
        } catch (NoSuchFieldError e) {
            getLogger().info("当前服务器版本低于 1.17，跳过注册粗铜块配方。");
        }

        getLogger().info("Raw Block Smelting 配方注册完成!");
    }

    private void addSmeltingRecipe(String keyName, Material source, Material result) {
        NamespacedKey key = new NamespacedKey(this, keyName);
        ItemStack resultStack = new ItemStack(result);
        // 经验值设为 1.0 (与烧炼矿石类似)，时间 200 ticks (标准熔炉时间)
        FurnaceRecipe recipe = new FurnaceRecipe(key, resultStack, source, 1.0f, 200); 
        Bukkit.addRecipe(recipe);
    }

     private void addBlastingRecipe(String keyName, Material source, Material result) {
        NamespacedKey key = new NamespacedKey(this, keyName);
        ItemStack resultStack = new ItemStack(result);
         // 经验值设为 1.0 (与烧炼矿石类似)，时间 100 ticks (标准高炉时间)
        BlastingRecipe recipe = new BlastingRecipe(key, resultStack, source, 1.0f, 100);
        Bukkit.addRecipe(recipe);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Raw Block Smelting 插件已卸载。"); // 添加卸载日志
    }
}
