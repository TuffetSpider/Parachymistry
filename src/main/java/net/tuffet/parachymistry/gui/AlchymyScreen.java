package net.tuffet.parachymistry.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CyclingSlotIcon;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;


import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.player.PlayerInventory;


import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;


import java.util.List;


@Environment(EnvType.CLIENT)
public class AlchymyScreen extends ForgingScreen<AlchymyScreenHandler> {
    private static final Identifier ERROR_TEXTURE = Identifier.ofVanilla("container/smithing/error");
    private static final Identifier EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE = Identifier.ofVanilla("item/empty_slot_smithing_template_armor_trim");
    private static final Identifier EMPTY_SLOT_SMITHING_TEMPLATE_NETHERITE_UPGRADE_TEXTURE = Identifier.ofVanilla("item/empty_slot_smithing_template_netherite_upgrade");
    private static final List<Identifier> EMPTY_SLOT_TEXTURES;
    private static final Vector3f field_45497;
    private static final Quaternionf WITCH_ROTATION;
    private final CyclingSlotIcon templateSlotIcon = new CyclingSlotIcon(0);
    private final CyclingSlotIcon baseSlotIcon = new CyclingSlotIcon(1);
    private final CyclingSlotIcon additionsSlotIcon = new CyclingSlotIcon(2);
    @Nullable
    private WitchEntity witch;

    public AlchymyScreen(AlchymyScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, Identifier.of(Parachymistry.MOD_ID,"textures/gui/sprites/alchemical_table.png"));
        this.titleX = 40;
        this.titleY = 15;
    }
    private void equipWitch(ItemStack stack) {
        if (this.witch != null) {
            EquipmentSlot[] var2 = EquipmentSlot.values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                EquipmentSlot equipmentSlot = var2[var4];
                this.witch.equipStack(equipmentSlot, ItemStack.EMPTY);
            }

            if (!stack.isEmpty()) {
                ItemStack itemStack = stack.copy();
                Item var8 = stack.getItem();
                if (var8 instanceof ArmorItem) {
                    ArmorItem armorItem = (ArmorItem)var8;
                    this.witch.equipStack(armorItem.getSlotType(), itemStack);
                } else {
                    this.witch.equipStack(EquipmentSlot.MAINHAND, itemStack);
                }
            }

        }
    }

    protected void setup() {
        assert this.client != null;
        assert this.client.world != null;
        this.witch = new WitchEntity(EntityType.WITCH,this.client.world);
        this.witch.bodyYaw = 210.0F;
        this.witch.setHeadYaw(210.0F);
        this.equipWitch(this.handler.getSlot(3).getStack());
    }
    public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
        if (slotId == 3) {
            this.equipWitch(stack);
        }}




    public void handledScreenTick() {
        super.handledScreenTick();
        this.templateSlotIcon.updateTexture(EMPTY_SLOT_TEXTURES);
        this.baseSlotIcon.updateTexture(EMPTY_SLOT_TEXTURES);
        this.additionsSlotIcon.updateTexture(EMPTY_SLOT_TEXTURES);
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }

    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        super.drawBackground(context, delta, mouseX, mouseY);
        this.templateSlotIcon.render(this.handler, context, delta, this.x, this.y);
        this.baseSlotIcon.render(this.handler, context, delta, this.x, this.y);
        this.additionsSlotIcon.render(this.handler, context, delta, this.x, this.y);
        InventoryScreen.drawEntity(context, (float)(this.x + 141), (float)(this.y + 75), 25.0F, field_45497, WITCH_ROTATION, (Quaternionf)null, this.witch);

    }


    protected void drawInvalidRecipeArrow(DrawContext context, int x, int y) {
        if (this.hasInvalidRecipe()) {
            context.drawGuiTexture(ERROR_TEXTURE, x + 65, y + 46, 28, 21);
        }

    }



    private boolean hasInvalidRecipe() {
        return this.handler.getSlot(0).hasStack() && this.handler.getSlot(1).hasStack() && this.handler.getSlot(2).hasStack() && !this.handler.getSlot(this.handler.getResultSlotIndex()).hasStack();
    }

    static {
        field_45497 = new Vector3f();
        WITCH_ROTATION = (new Quaternionf()).rotationXYZ(0.43633232F, 0.0F, 3.1415927F);
        EMPTY_SLOT_TEXTURES = List.of(EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE, EMPTY_SLOT_SMITHING_TEMPLATE_NETHERITE_UPGRADE_TEXTURE);
    }
}

