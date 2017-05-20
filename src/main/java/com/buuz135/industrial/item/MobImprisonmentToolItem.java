package com.buuz135.industrial.item;

import com.buuz135.industrial.proxy.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class MobImprisonmentToolItem extends IFCustomItem {

    public MobImprisonmentToolItem() {
        super("mob_imprisonment_tool");
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.getEntityWorld().isRemote) return EnumActionResult.FAIL;
        if (!containsEntity(stack)) return EnumActionResult.FAIL;
        Entity entity = getEntitFromStack(stack, worldIn, true);
        BlockPos blockPos = pos.offset(facing);
        entity.setPositionAndRotation(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, 0, 0);
        stack.setTagCompound(new NBTTagCompound());
        player.setHeldItem(hand, stack);
        worldIn.spawnEntity(entity);
        if (entity instanceof EntityLiving) ((EntityLiving) entity).playLivingSound();
        return EnumActionResult.SUCCESS;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target.getEntityWorld().isRemote) return false;
        if (target instanceof EntityPlayer) return false;
        if (containsEntity(stack)) return false;
        String entityID = EntityList.getKey(target).toString();
        if (isBlacklisted(entityID)) return false;
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("entity", entityID);
        nbt.setInteger("id", EntityList.getID(target.getClass()));
        target.writeToNBT(nbt);
        stack.setTagCompound(nbt);
        playerIn.swingArm(hand);
        playerIn.setHeldItem(hand, stack);
        target.setDead();
        return true;
    }


    public boolean isBlacklisted(String entity) {
        return false;
    }

    public boolean containsEntity(ItemStack stack) {
        return !stack.isEmpty() && stack.hasTagCompound() && stack.getTagCompound().hasKey("entity");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        if (containsEntity(stack)) {
            tooltip.add("Mob: " + stack.getTagCompound().getString("entity"));
            tooltip.add("Health: " + stack.getTagCompound().getDouble("Health"));
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
        }
    }

    public Entity getEntitFromStack(ItemStack stack, World world, boolean withInfo) {
        Entity entity = EntityList.createEntityByIDFromName(new ResourceLocation(stack.getTagCompound().getString("entity")), world);
        if (withInfo) entity.readFromNBT(stack.getTagCompound());
        return entity;
    }

    @Override
    public IRecipe getRecipe() {
        return new ShapedRecipes(3,3,new ItemStack[]{ItemStack.EMPTY, new ItemStack(ItemRegistry.plastic),ItemStack.EMPTY,
                new ItemStack(ItemRegistry.plastic), new ItemStack(Items.GHAST_TEAR), new ItemStack(ItemRegistry.plastic),
                ItemStack.EMPTY, new ItemStack(ItemRegistry.plastic),ItemStack.EMPTY},new ItemStack(this));
    }
}
