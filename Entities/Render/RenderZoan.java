package MineMineNoMi3.Entities.Render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import MineMineNoMi3.Entities.Models.ModelZoan;

public class RenderZoan extends RenderPlayer
{
	
	public RenderZoan()
	{
		this.mainModel = new ModelZoan();	
	}

	public void renderFirstPersonArm(EntityPlayer entityPlayer)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glPushMatrix();   
		((ModelBiped)this.mainModel).bipedRightArm.render(0.0625F);
		GL11.glPopMatrix();
	}	

	protected int shouldRenderPass(EntityLivingBase entityLivingBase, int renderPass, float partialTickTime)
	{
		return -1;
	}
	
	public void preRenderCallback(AbstractClientPlayer clientPlayer, float partialTickTime)
	{
		/*RenderPlayerEvent.Specials.Pre event = new RenderPlayerEvent.Specials.Pre(clientPlayer, this, partialTickTime);
		if (MinecraftForge.EVENT_BUS.post(event))
			return;
		super.renderArrowsStuckInEntity(clientPlayer, partialTickTime);
		ItemStack itemstack = clientPlayer.inventory.getCurrentItem();
		if (itemstack != null && event.renderItem)
			this.renderManager.itemRenderer.renderItem(clientPlayer, itemstack, 0);*/
	}
	
	public void doRender(Entity ent, double x, double y, double z, float yaw, float pitch)
	{
		super.doRender(ent, x, y, z, yaw, pitch);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glPushMatrix(); 

		
		float wrappedRotation = handleRotationFloat((EntityLivingBase) ent, pitch);

		rotateCorpse((EntityLivingBase) ent, wrappedRotation, pitch, pitch);

		((ModelZoan)this.mainModel).bison_body.render(0.0625F);
		((ModelZoan)this.mainModel).bipedBody.showModel = true;
		
		GL11.glTranslatef(0.0F, 1.7F, 0.0F);
		GL11.glTranslatef(0.0F, -0.1F, 0.0F);
		GL11.glScalef(0.7F, 0.7F, -0.7F);
		GL11.glRotatef(90F, 0, 0, 0.0F);
		GL11.glColor4d(255/255, 0/255, 0/255, 255);
		
		GL11.glPopMatrix();
		
/*		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glPushMatrix(); 
		
		EntityLivingBase entity = (EntityLivingBase)ent;
		
		float limbSwing = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * pitch;
		float limbAngle = entity.limbSwing - entity.limbSwingAmount * (1.0F - pitch);
		float realRotationPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * pitch;
		float realRenderYaw = interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, pitch);
		float realRenderYawHead = interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, pitch);
		float wrappedRotation = handleRotationFloat(entity, pitch);
		
		renderLivingAt(entity, x, y, z);
		rotateCorpse(entity, wrappedRotation, realRenderYaw, pitch);
		
		GL11.glTranslatef(0.0F, 1.7F, 0.0F);
		GL11.glTranslatef(0.0F, -0.1F, 0.0F);
		GL11.glScalef(0.7F, 0.7F, -0.7F);
		
		this.mainModel.setLivingAnimations((EntityLivingBase) entity, 0, 0, 0);
		this.renderModel((EntityLivingBase) entity, 0, 0, 0, 0, 0, -0.0925F);
		try
		{
			this.renderEquippedItems((AbstractClientPlayer) entity, 0);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
		
		this.mainModel.render(entity, 0, 0, 0, 0, 0, -0.0925F);
		GL11.glPopMatrix();*/
	}
	
	/*private float interpolateRotation(float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		for (float multiplier = unknownFloat2 - unknownFloat1; multiplier < -180.0F;)
		{
			while (multiplier >= 180.0F)
		      multiplier -= 360.0F;
		    return unknownFloat1 + unknownFloat3 * multiplier;
		}
		return unknownFloat3;
	}*/
	  	
	
}
