package com.builtbroken.bagableplants;

import com.builtbroken.bagableplants.handler.InteractionHandler;
import com.builtbroken.bagableplants.handler.VanillaHandler;
import com.builtbroken.bagableplants.handler.atm.AppleTeaMilkHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 1/3/2017.
 */
@Mod(modid = "bagableplants", name = "Bagable Plants", version = BagablePlants.VERSION)
public class BagablePlants
{
    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    public static Item itemBag;

    public static final HashMap<Object, InteractionHandler> blockNameToHandler = new HashMap();

    /**
     * Called to register a block with a handler
     *
     * @param block   - block instance
     * @param handler - handler instance
     * @return true if registered
     */
    public static boolean register(Block block, InteractionHandler handler)
    {
        if (block != null && handler != null)
        {
            blockNameToHandler.put(block, handler);
            return true;
        }
        return false;
    }

    public static boolean register(Item item, InteractionHandler handler)
    {
        if (item != null && handler != null)
        {
            blockNameToHandler.put(item, handler);
            return true;
        }
        return false;
    }

    /**
     * Called to register a block with a handler
     *
     * @param name    - registered name of the block
     * @param handler - handler
     * @return true if registered
     */
    public static boolean register(String name, InteractionHandler handler)
    {
        if (name != null && handler != null)
        {
            Block block = (Block) Block.blockRegistry.getObject(name);
            if (block != null)
            {
                blockNameToHandler.put(block, handler);
                return true;
            }
        }
        return false;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        itemBag = new ItemBag();
        GameRegistry.registerItem(itemBag, "bpPlantBag");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        VanillaHandler.register();
        if (Loader.isModLoaded("DCsAppleMilk"))
        {
            AppleTeaMilkHandler.register();
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        GameRegistry.addShapedRecipe(new ItemStack(itemBag), "LSL", "LBL", " L ", 'L', Items.leather, 'B', Items.bowl, 'S', Items.string);
    }
}
