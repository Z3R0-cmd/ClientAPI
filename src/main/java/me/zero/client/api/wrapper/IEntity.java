package me.zero.client.api.wrapper;

import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;

/**
 * Used to convert entity position and rotation
 * to the Client API Vectors
 *
 * @since 1.0
 *
 * Created by Brady on 2/25/2017.
 */
public interface IEntity {

    /**
     * Sets the entity position
     *
     * @since 1.0
     *
     * @param pos New position
     */
    void setPos(Vec3 pos);

    /**
     * Sets the previous entity position
     *
     * @since 1.0
     *
     * @param pos New position
     */
    void setPrevPos(Vec3 pos);

    /**
     * Sets the last tick entity position
     *
     * @since 1.0
     *
     * @param pos New position
     */
    void setLastTickPos(Vec3 pos);

    /**
     * Sets the entity rotations
     *
     * @since 1.0
     *
     * @param rotations New position
     */
    void setRotations(Vec2 rotations);

    /**
     * Sets the previous entity rotations
     *
     * @since 1.0
     *
     * @param rotations New position
     */
    void setPrevRotations(Vec2 rotations);

    /**
     * Converts the entity position to a {@code Vec3}
     *
     * @since 1.0
     *
     * @return Position as a {@code Vec3}
     */
    Vec3 getPos();

    /**
     * Converts the previous entity position to a {@code Vec3}
     *
     * @since 1.0
     *
     * @return Previous position as a {@code Vec3}
     */
    Vec3 getPrevPos();

    /**
     * Converts the last tick entity position to a {@code Vec3}
     *
     * @since 1.0
     *
     * @return Last tick position as a {@code Vec3}
     */
    Vec3 getLastTickPos();

    /**
     * Calculates the predicted position that an entity will
     * be in the specified amount of ticks
     *
     * @since 1.0
     *
     * @param ticks Time in ticks predicted
     * @return The predicted position
     */
    Vec3 interpolate(float ticks);

    /**
     * Converts the entity rotation angles to a {@code Vec2}
     *
     * @since 1.0
     *
     * @return Rotations as a {@code Vec2}
     */
    Vec2 getRotations();

    /**
     * Converts the previous entity rotation angles to a {@code Vec2}
     *
     * @since 1.0
     *
     * @return Previous rotations as a {@code Vec2}
     */
    Vec2 getPrevRotations();

    /**
     * Sets all forms of position and rotation in
     * this entity to the specified position and rotation
     *
     * @since 1.0
     *
     * @param pos The position
     * @param angles The rotation
     */
    default void setAll(Vec3 pos, Vec2 angles) {
        this.setAll(pos, pos, pos, angles, angles);
    }

    /**
     * Sets all forms of position of rotation to
     * their specified vectors
     *
     * @since 1.0
     *
     * @param pos The position
     * @param prev The previous position
     * @param lastTick The last tick position
     * @param angles The angles
     * @param prevAngles The previous angles
     */
    default void setAll(Vec3 pos, Vec3 prev, Vec3 lastTick, Vec2 angles, Vec2 prevAngles) {
        this.setPos(pos);
        this.setPrevPos(prev);
        this.setLastTickPos(lastTick);
        this.setRotations(angles);
        this.setPrevRotations(prevAngles);
    }
}