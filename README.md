#  SDM Ore Stages

**SDM Ore Stages** is a modern reworking of the classic mod [Ore Stages](https://www.curseforge.com/minecraft/mc-mods/ore-stages),
adapted for **new versions of Minecraft (1.21 and higher)** and fully integrated into the ecosystem of **SDM Stages**.

The mod allows you to **restrict access to blocks step by step** — hide, replace or make them inaccessible
until the player reaches the desired stage of progression.

## Main Features

- **Step—by-step progression system** through **SDM Stages** - No more GameStages required.
- **Hiding and replacing blocks**: For example, `diamond_ore` can be turned into `stone` before opening the stage.
- **CraftTweaker and KubeJS support** for flexible configuration.
- Full support for `BlockState` — states can be set (`snowy=false`, `axis=y`, etc.).

## Requirements

- [**SDM Stages**](https://www.curseforge.com/minecraft/mc-mods/sdm-stages)
- [**CraftTweaker**](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) *or* [**KubeJS**](https://www.curseforge.com/minecraft/mc-mods/kubejs)

> ⚠️ Only versions of **Minecraft 1.21 and higher** are supported.  
> Older versions (1.20.x and below) **are not supported** and will not work. (Maybe make a port sometime later.)

## How it works ?

If the player ** does not have the required stage**:
- The block looks like a **substitute for** (for example, `stone` instead of `diamond_ore`).
- The player **cannot interact** with him (PCM, breaking, activation).
- When destroyed, the block drops its substitute`s items.
- The digging speed is the same as the substitute speed.
- If the block breaks without the players participation (explosion, liquid, car), the "default behavior" is used.

## Examples: KubeJS

```js
// server_scripts/example.js

// Replaces the grass with an oak log until the "one" stage is opened
OreStages.addOreStageBlockReplaced("one", "minecraft:grass_block", "minecraft:oak_log");

// Replaces iron ore with stone until the "two" stage is opened
OreStages.addOreStageBlockReplaced("two", "minecraft:iron_ore", "minecraft:stone");

// Hides diamond ore up to the "three" stage
OreStages.addOreStageBlockState("three", "minecraft:diamond_ore");

/*
OreStages.addOreStageBlock(stage as String, hidden as Block);            // By default, stone or deep slate is used.        
OreStages.addOreStageBlockState(stage as String, hidden as BlockState);  // By default, stone or deep slate is used.       

OreStages.addOreStageBlockReplaced(stage as String, hidden as Block, replaced as Block);
OreStages.addOreStageBlockStateReplaced(stage as String, hidden as BlockState, replaced as BlockState);
*/
```

## Examples: CraftTweaker

```ts
import mods.orestages.OreStages;

// Replaces the grass with an oak log until the "one" stage is opened
OreStages.addOreStage("one", <blockstate:minecraft:grass_block:snowy=false>, <blockstate:minecraft:oak_log:axis=y>);

// Replaces iron ore with stone until the "two" stage is opened
OreStages.addOreStage("two", <block:minecraft:iron_ore>, <block:minecraft:stone>);

// Hides diamond ore up to the "three" stage
OreStages.addOreStage("three", <block:minecraft:diamond_ore>);

/*
OreStages.addOreStage(stage as string, hidden as Block);          // By default, stone or deep slate is used.        
OreStages.addOreStage(stage as string, hidden as BlockState);     // By default, stone or deep slate is used.    

OreStages.addOreStage(stage as string, hidden as Block, replaced as Block);
OreStages.addOreStage(stage as string, hidden as BlockState, replaced as BlockState);
*/
```

## Integrations

| Mod              | Description                             |
|------------------|-----------------------------------------|
| **SDM Stages**   | The stage system and player progression |
| **CraftTweaker** | Configuration via ZS scripts            |
| **KubeJS**       | Configuration via JS scripts            |

## A note for modpackers

The mod is perfect for:

* RPG/Quest builds with gradual discovery of ores and resources.
* Educational modpacks where new players learn mechanics step by step.
* Servers with a progression or tech-tree balance.

## The original

This project is based on the original mod [**Ore Stages**](https://www.curseforge.com/minecraft/mc-mods/ore-stages )
(author: **Darkhax**), but completely **rewritten and updated** for modern versions of Minecraft,
with integration into the SDM API and improved performance.

