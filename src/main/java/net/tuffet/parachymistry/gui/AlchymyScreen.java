package net.tuffet.parachymistry.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CyclingSlotIcon;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;


import net.minecraft.entity.player.PlayerInventory;




import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;


import java.util.List;


@Environment(EnvType.CLIENT)
public class AlchymyScreen extends ForgingScreen<AlchymyScreenHandler> {
    private static final Identifier ERROR_TEXTURE = Identifier.ofVanilla("container/smithing/error");
    private static final Identifier EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE = Identifier.ofVanilla("item/empty_slot_smithing_template_armor_trim");
    private static final Identifier EMPTY_SLOT_SMITHING_TEMPLATE_NETHERITE_UPGRADE_TEXTURE = Identifier.ofVanilla("item/empty_slot_smithing_template_netherite_upgrade");
    private static final List<Identifier> EMPTY_SLOT_TEXTURES;
    private final CyclingSlotIcon templateSlotIcon = new CyclingSlotIcon(0);
    private final CyclingSlotIcon baseSlotIcon = new CyclingSlotIcon(1);
    private final CyclingSlotIcon additionsSlotIcon = new CyclingSlotIcon(2);

    public AlchymyScreen(AlchymyScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, Text.of("Create Alchymical Wonders!"), Identifier.of(Parachymistry.MOD_ID,"textures/gui/sprites/alchemical_table.png"));
        this.titleX = 33;
        this.titleY = 15;
    }



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
    }


    protected void drawInvalidRecipeArrow(DrawContext context, int x, int y) {
        if (this.hasInvalidRecipe()) {
            context.drawGuiTexture(ERROR_TEXTURE, x + 65, y + 46, 28, 21);
        }

    }



    private boolean hasInvalidRecipe() {
        return ((AlchymyScreenHandler)this.handler).getSlot(0).hasStack() && ((AlchymyScreenHandler)this.handler).getSlot(1).hasStack() && ((AlchymyScreenHandler)this.handler).getSlot(2).hasStack() && !((AlchymyScreenHandler)this.handler).getSlot(((AlchymyScreenHandler)this.handler).getResultSlotIndex()).hasStack();
    }

    static {
        EMPTY_SLOT_TEXTURES = List.of(EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE, EMPTY_SLOT_SMITHING_TEMPLATE_NETHERITE_UPGRADE_TEXTURE);
    }
}

