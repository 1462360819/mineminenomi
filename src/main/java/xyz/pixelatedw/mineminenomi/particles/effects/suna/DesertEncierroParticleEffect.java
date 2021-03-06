package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class DesertEncierroParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double t = 0;
		double x, y, z;
		Random rand = world.rand;

		while (t < 1)
		{
			t += 0.5 * Math.PI;

			for (double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				z = t * Math.sin(theta);

				motionX = -x / 6;
				motionY = -0.1 + (rand.nextDouble() / 10);
				motionZ = -z / 6;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.SUNA);
				data.setLife(-1);
				data.setSize(3.3F);
				data.setMotion(motionX, motionY, motionZ);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 1.25), posY + 1.2, posZ + (z * 1.25));
				
				data = new GenericParticleData();
				data.setTexture(ModResources.SUNA);
				data.setLife(-1);
				data.setSize(3.3F);
				data.setMotion(motionX, motionY, motionZ);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 1.25), posY + 2.2, posZ + (z * 1.25));
			}
		}
	}

}
