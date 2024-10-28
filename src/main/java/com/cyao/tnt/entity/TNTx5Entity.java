package com.cyao.tnt.entity;

import com.cyao.CustomBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TNTx5Entity extends TntEntity implements Ownable {
    private static final float EXPLOSION_POWER = 20.0F;
    private boolean teleported;
    private static final ExplosionBehavior TELEPORTED_EXPLOSION_BEHAVIOR;

    public TNTx5Entity(EntityType<? extends TNTx5Entity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
    }

    public TNTx5Entity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(world, x, y, z, igniter);
        super.setBlockState(CustomBlocks.TNTx5.getDefaultState());
    }


    @Override
    public void tick() {
        this.tickPortalTeleportation();
        this.applyGravity();
        this.move(MovementType.SELF, this.getVelocity());
        this.tickBlockCollision();
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    private void explode() {
        this.getWorld().createExplosion(this, Explosion.createDamageSource(this.getWorld(), this), this.teleported ? TELEPORTED_EXPLOSION_BEHAVIOR : null, this.getX(), this.getBodyY(0.0625), this.getZ(), EXPLOSION_POWER, false, World.ExplosionSourceType.TNT);
    }

    @Nullable
    public Entity teleportTo(TeleportTarget teleportTarget) {
        Entity entity = super.teleportTo(teleportTarget);
        if (entity instanceof TNTx5Entity tntEntity) {
            tntEntity.setTeleported();
        }

        return entity;
    }

    private void setTeleported() {
        this.teleported = true;
    }

    static {
        TELEPORTED_EXPLOSION_BEHAVIOR = new ExplosionBehavior() {
            public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                return !state.isOf(Blocks.NETHER_PORTAL) && super.canDestroyBlock(explosion, world, pos, state, power);
            }

            public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
                return blockState.isOf(Blocks.NETHER_PORTAL) ? Optional.empty() : super.getBlastResistance(explosion, world, pos, blockState, fluidState);
            }
        };
    }
}
