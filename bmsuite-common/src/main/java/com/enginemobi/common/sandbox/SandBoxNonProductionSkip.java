package com.enginemobi.common.sandbox;

/**
 * The workflow code will not persist an entity if the changes detected are limited to fields whose type is assignable
 * from this interface, and the persistence is being requested for a production record outside of a production deploy
 * step.
 *
 */
public interface SandBoxNonProductionSkip {
}
