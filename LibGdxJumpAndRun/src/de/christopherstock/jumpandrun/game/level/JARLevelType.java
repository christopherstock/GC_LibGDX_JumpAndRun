/*  $Id: JARLevelType.java 196 2014-04-21 10:01:19Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.level;

    import  java.util.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.CollisionType;
    import  de.christopherstock.jumpandrun.game.object.*;
    import  de.christopherstock.jumpandrun.game.object.JARGameObject.*;
    import  de.christopherstock.jumpandrun.game.object.JARPlayer.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.JARScreen.DrawingLayer;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.math.geom.LibRect2D.Elevation;

    /*****************************************************************************
    *   Represents a level.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/level/JARLevelType.java $
    *****************************************************************************/
    public enum JARLevelType
    {
        EEnchantedWoods(        1300,   -1,     10000,  2500,   JARImage.EBgForest2,        JARImage.EBgTrees1, StandingDirection.ERight    ),
        EForestFrenzy(          0,      -1,     5000,   750,    JARImage.EBgForest1,        JARImage.EBgTrees1, StandingDirection.ELeft     ),
        EWaterlooLake(          1216,   -1,     5000,   750,    JARImage.EBgForest1,        JARImage.EBgTrees1, StandingDirection.ELeft     ),
        EPowderHills(           0,      0,      5000,   750,    JARImage.EBgForest1,        JARImage.EBgTrees1, StandingDirection.ERight    ),
        ETurtleBeach(           1216,   -1,     5000,   750,    JARImage.EBgForest1,        JARImage.EBgTrees1, StandingDirection.ELeft     ),
        EGloomyGrove(           1216,   -1,     5000,   750,    JARImage.EBgForest1,        JARImage.EBgTrees1, StandingDirection.ELeft     ),
        EIcyCaverns(            1216,   -1,     5000,   750,    JARImage.EBgForest1,        JARImage.EBgTrees1, StandingDirection.ELeft     ),
        EPsychedelicCopse(      1216,   -1,     5000,   750,    JARImage.EBgForest1,        JARImage.EBgTrees1, StandingDirection.ELeft     ),
        EFrighteningFacility(   1216,   -1,     5000,   750,    JARImage.EBgForest1,        JARImage.EBgTrees1, StandingDirection.ELeft     ),

        ;

        public                      int                 iLevelWidth                                 = 0;
        public                      int                 iLevelHeight                                = 0;
        public                      int                 iStartX                                     = 0;
        public                      int                 iStartY                                     = 0;

        public                      JARSprite           iSpriteBg                                   = null;
        public                      JARSprite           iSpriteMiddle                               = null;

        public                      StandingDirection   iStartupStandingDirection                   = null;

        private JARLevelType( int aStartX, int aStartY, int aLevelWidth, int aLevelHeight, JARImage aImgBg, JARImage aImgMiddle, StandingDirection aStartupStandingDirection )
        {
            iStartX                     = aStartX;
            iStartY                     = aStartY;
            iLevelWidth                 = aLevelWidth;
            iLevelHeight                = aLevelHeight;

            iSpriteBg                   = new JARSprite( aImgBg     );
            iSpriteMiddle               = new JARSprite( aImgMiddle );

            iStartupStandingDirection   = aStartupStandingDirection;
        }

        public final JARWall[] getOriginalWalls()
        {
            Vector<JARWall> ret = new Vector<JARWall>();

            switch ( this )
            {
                case EEnchantedWoods:
                {
                    //grass
                    ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 64, 12, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 1920, iLevelHeight - 64, 10, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 3712, iLevelHeight - 64, 20, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                  //ret.addAll( JARLevelObjects.createFloor( 6896, iLevelHeight - 64, 20, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );

                    //bridges
                    ret.addAll( JARLevelObjects.createBridge( 1536, iLevelHeight - 64, 1 ) );
                    ret.addAll( JARLevelObjects.createBridge( 3200, iLevelHeight - 64, 2 ) );
                  //ret.addAll( JARLevelObjects.createBridge( 6256, iLevelHeight - 64, 3 ) );

                    //signposts
                    ret.add(    new JARWall( 475,   iLevelHeight - 212,  JARImage.EDecoSignpost1,    CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer, false ) );
                    ret.add(    new JARWall( 2000,  iLevelHeight - 212,  JARImage.EDecoSignpost1,    CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer, false ) );
                    ret.add(    new JARWall( 3750,  iLevelHeight - 212,  JARImage.EDecoSignpost1,    CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer, false ) );

                    //trees
                    ret.add(    new JARWall( 675,   iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer, false ) );
                    ret.add(    new JARWall( 2300,  iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer, false ) );
                    ret.add(    new JARWall( 4250,  iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer, false ) );

                    //steps
                    ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 256,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 128,  iLevelHeight - 452,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );

                    ret.addAll( JARLevelObjects.createFloor( 256,  iLevelHeight - 648,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 384,  iLevelHeight - 844,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 512,  iLevelHeight - 1040, 1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 640,  iLevelHeight - 1236, 1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 768,  iLevelHeight - 1432,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 896,  iLevelHeight - 1628, 1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 1024, iLevelHeight - 1824, 1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 1152, iLevelHeight - 2020, 1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );

                    break;
                }

                case EForestFrenzy:
                {
                    //grass
                    ret.addAll( JARLevelObjects.createFloor( 0,     iLevelHeight - 64,   2, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 896,   iLevelHeight - 384,  4, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createFloor( 2048,  iLevelHeight - 64,   4, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );

                    //stairs
                    ret.addAll( JARLevelObjects.createElevation( 256,  iLevelHeight - 128, 5, Elevation.EAscending,  JARImage.EWallGrass1Ascending,  JARImage.EWallGrass1ToppingAscending,  CollisionType.EColliding ) );
                    ret.addAll( JARLevelObjects.createElevation( 1408, iLevelHeight - 384, 5, Elevation.EDescending, JARImage.EWallGrass1Descending, JARImage.EWallGrass1ToppingDescending, CollisionType.EColliding ) );

                    //pass-through 1st floor
                  //ret.addAll( JARLevelObjects.createFloor( 0,     iLevelHeight - 304,  2, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EPassThrough ) );
                    ret.addAll( JARLevelObjects.createFloor( 1024,  iLevelHeight - 624,  4, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EPassThrough ) );
                    ret.addAll( JARLevelObjects.createFloor( 2048,  iLevelHeight - 304,  4, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EPassThrough ) );

                    //pass-through stairs
                    ret.addAll( JARLevelObjects.createElevation( 768, iLevelHeight - 624, 2, Elevation.EAscending,  JARImage.EWallGrass1Ascending,  JARImage.EWallGrass1ToppingAscending,  CollisionType.EPassThrough ) );

                    //swinging block
                    ret.add( new JARWall( 100, iLevelHeight - 250, JARImage.EWallWood1, CollisionType.EPassThrough, DrawingLayer.EBeforePlayer, true ) );




                    //filler stones
                    //ret.addAll( JARLevelObjects.createFloor( 256,   iLevelHeight - 64,  3, JARImage.EWallStone1, null ) );
/*
                    //ascending / descending
                    ret.add(    new JARWall( 256,  iLevelHeight - 128, JARImage.EWallGrass1Ascending,           CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
                    ret.add(    new JARWall( 256,  iLevelHeight - 192, JARImage.EWallGrass1ToppingAscending,    CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );

                    ret.add(    new JARWall( 384,  iLevelHeight - 192, JARImage.EWallGrass1Ascending,           CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
                    ret.add(    new JARWall( 384,  iLevelHeight - 256, JARImage.EWallGrass1ToppingAscending,    CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );

                    ret.add(    new JARWall( 1024, iLevelHeight - 192, JARImage.EWallGrass1Descending,          CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
                    ret.add(    new JARWall( 1024, iLevelHeight - 256, JARImage.EWallGrass1ToppingDescending,   CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );

                    ret.add(    new JARWall( 1152, iLevelHeight - 128, JARImage.EWallGrass1Descending,          CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
                    ret.add(    new JARWall( 1152, iLevelHeight - 192, JARImage.EWallGrass1ToppingDescending,   CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );
*/
                    break;
                }

                case EWaterlooLake:
                {
                    //floor 'water'
                    ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 64, 15, JARImage.EWallWater1, null, CollisionType.EColliding ) );

                    //block
                    ret.addAll( JARLevelObjects.createBlock(  128, iLevelHeight - 256, 6, 2, JARImage.EWallStone1, CollisionType.EColliding ) );

                    break;
                }

                default:
                {
                    ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 64, 12, JARImage.EWallGrass1, null, CollisionType.EColliding ) );
                  //ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 64, 12, JARImage.EWallGrass1, JARImage.EWallGrass1Topping, CollisionType.EColliding ) );

                    //trees
                    ret.add(    new JARWall( 100,  iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer,     false ) );
                    ret.add(    new JARWall( 500,  iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBehindPlayer,     false ) );
                    ret.add(    new JARWall( 900,  iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer,     false ) );

                    break;
                }
            }

            return ret.toArray( new JARWall[] {} );
        }

        public final JARPlayer[] getOriginalEnemies()
        {
            JARPlayer[] ret = null;

            switch ( this )
            {
                case EEnchantedWoods:
                {
                    ret = new JARPlayer[]
                    {
                        //new JARPlayer( 2000, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyKremlinGreen,   StandingDirection.ELeft     ),
                        //new JARPlayer( 2120, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyKRool,          StandingDirection.ELeft     ),

                        new JARPlayer( 2240, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyHedgehog,       StandingDirection.ELeft     ),
                        new JARPlayer( 2360, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyRat,            StandingDirection.ELeft     ),

                        new JARPlayer( 2480, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyBigGuy,         StandingDirection.ELeft     ),

                      //new JARPlayer( 2480, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyHedgehog,       StandingDirection.ELeft     ),
                      //new JARPlayer( 2600, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyRat,            StandingDirection.ELeft     ),
                      //new JARPlayer( 2720, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyHedgehog,       StandingDirection.ELeft     ),
                      //new JARPlayer( 2840, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyRat,            StandingDirection.ELeft     ),
                    };
                    break;
                }

                case EForestFrenzy:
                {
                    ret = new JARPlayer[]
                    {
/*
                        new JARPlayer( 3000, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyStrong, StandingDirection.ELeft     ),
                        new JARPlayer( 500,  -1, GameObjectType.EEnemy, JARPlayerType.EEnemyWeak,   StandingDirection.ELeft     ),
                        new JARPlayer( 1000, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyWeak,   StandingDirection.ELeft     ),
                        new JARPlayer( 1500, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyWeak,   StandingDirection.ELeft     ),
*/
                    };
                    break;
                }

                case EWaterlooLake:
                {
                    ret = new JARPlayer[]
                    {
                    };
                    break;
                }

                default:
                {
                    ret = new JARPlayer[]
                    {
                        //new JARPlayer( 500, 0, GameObjectType.EEnemy, JARPlayerType.EEnemyKremlinGreen, StandingDirection.ELeft ),
                    };
                    break;
                }
            }

            return ret;
        }

        public final JARItem[] getOriginalItems()
        {
            JARItem[] ret = null;

            switch ( this )
            {
                case EEnchantedWoods:
                {
                    ret = new JARItem[]
                    {
                        new JARItem( 150,   iLevelHeight - 300,     JARItemType.ECoin,          false   ),
                        new JARItem( 250,   iLevelHeight - 300,     JARItemType.ECoin,          false   ),
                        new JARItem( 350,   iLevelHeight - 300,     JARItemType.ECoin,          false   ),
                        new JARItem( 550,   iLevelHeight - 300,     JARItemType.ECherry,        false   ),
                        new JARItem( 650,   iLevelHeight - 300,     JARItemType.ECherry,        false   ),
                        new JARItem( 750,   iLevelHeight - 300,     JARItemType.ECherry,        false   ),
                        new JARItem( 1050,  iLevelHeight - 300,     JARItemType.EApple,         false   ),
                        new JARItem( 1150,  iLevelHeight - 300,     JARItemType.EApple,         false   ),
                        new JARItem( 1250,  iLevelHeight - 300,     JARItemType.EApple,         false   ),
                        new JARItem( 1550,  iLevelHeight - 300,     JARItemType.EOrange,        false   ),
                        new JARItem( 1650,  iLevelHeight - 300,     JARItemType.EOrange,        false   ),
                        new JARItem( 1750,  iLevelHeight - 300,     JARItemType.EOrange,        false   ),
                        new JARItem( 2050,  iLevelHeight - 300,     JARItemType.EPear,          false   ),
                        new JARItem( 2150,  iLevelHeight - 300,     JARItemType.EPear,          false   ),
                        new JARItem( 2250,  iLevelHeight - 300,     JARItemType.EPear,          false   ),
                        new JARItem( 2550,  iLevelHeight - 300,     JARItemType.EStrawberry,    false   ),
                        new JARItem( 2650,  iLevelHeight - 300,     JARItemType.EStrawberry,    false   ),
                        new JARItem( 2750,  iLevelHeight - 300,     JARItemType.EStrawberry,    false   ),

                        new JARItem( 1200,  300,                    JARItemType.ECoin,          true    ),
                    };
                    break;
                }

                case EForestFrenzy:
                {
                    ret = new JARItem[]
                    {
                    };
                    break;
                }

                case EWaterlooLake:
                {
                    ret = new JARItem[]
                    {
                        new JARItem( 1300, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1364, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1428, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1492, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1556, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1620, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1684, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1748, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1812, iLevelHeight - 200, JARItemType.ECoin, false ),
                        new JARItem( 1876, iLevelHeight - 200, JARItemType.ECoin, false ),

                        new JARItem( 1300, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1364, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1428, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1492, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1556, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1620, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1684, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1748, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1812, iLevelHeight - 264, JARItemType.ECoin, false ),
                        new JARItem( 1876, iLevelHeight - 264, JARItemType.ECoin, false ),

                        new JARItem( 1300, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1364, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1428, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1492, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1556, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1620, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1684, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1748, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1812, iLevelHeight - 328, JARItemType.ECoin, false ),
                        new JARItem( 1876, iLevelHeight - 328, JARItemType.ECoin, false ),
                    };

                    break;
                }

                default:
                {
                    ret = new JARItem[]
                    {
                        new JARItem( 300,  iLevelHeight - 150, JARItemType.ECoin, false ),
                        new JARItem( 364,  iLevelHeight - 150, JARItemType.ECoin, false ),
                    };
                    break;
                }
            }

            return ret;
        }
    }
