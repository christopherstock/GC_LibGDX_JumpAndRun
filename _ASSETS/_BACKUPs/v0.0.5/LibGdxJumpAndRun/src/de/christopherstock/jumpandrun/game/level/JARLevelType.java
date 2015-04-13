/*  $Id: JARLevelType.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.level;

    import  java.util.*;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.CollisionType;
    import  de.christopherstock.jumpandrun.game.JARGameObject.GameObjectType;
    import  de.christopherstock.jumpandrun.game.JARPlayer.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.JARScreen.DrawingLayer;

    /*****************************************************************************
    *   Represents a level.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/level/JARLevelType.java $
    *****************************************************************************/
    public enum JARLevelType
    {
        EEnchantedWoods(    1300,   -1,       10000,  1500,   JARImage.EBgForest2,      JARImage.EBgTrees1, StandingDirection.ERight ),
        EForestFrenzy(      0,      -1,       5000,   750,    JARImage.EBgForest1,      JARImage.EBgTrees1, StandingDirection.ELeft  ),
        EWaterlooLake(      1216,   -1,       5000,   750,    JARImage.EBgForest1,      JARImage.EBgTrees1, StandingDirection.ELeft  ),
        EPowderHills(       1216,   -1,       5000,   750,    JARImage.EBgForest1,      JARImage.EBgTrees1, StandingDirection.ELeft  ),
        ETurtleBeach(       1216,   -1,       5000,   750,    JARImage.EBgForest1,      JARImage.EBgTrees1, StandingDirection.ELeft  ),
        EGloomyGrove(       1216,   -1,       5000,   750,    JARImage.EBgForest1,      JARImage.EBgTrees1, StandingDirection.ELeft  ),

        ;

        public                      int                 iLevelWidth                                 = 0;
        public                      int                 iLevelHeight                                = 0;
        public                      int                 iStartX                                     = 0;
        public                      int                 iStartY                                     = 0;

        public                      JARImage            iImgBg                                      = null;
        public                      JARImage            iImgMiddle                                  = null;

        public                      StandingDirection   iStartupStandingDirection                   = null;

        private JARLevelType( int aStartX, int aStartY, int aLevelWidth, int aLevelHeight, JARImage aImgBg, JARImage aImgMiddle, StandingDirection aStartupStandingDirection )
        {
            iStartX                     = aStartX;
            iStartY                     = aStartY;
            iLevelWidth                 = aLevelWidth;
            iLevelHeight                = aLevelHeight;

            iImgBg                      = aImgBg;
            iImgMiddle                  = aImgMiddle;

            iStartupStandingDirection   = aStartupStandingDirection;
        }

        public final JARWall[] getOriginalWalls()
        {
            Vector<JARWall> ret = new Vector<JARWall>();

            switch ( this )
            {
                case EEnchantedWoods:
                {
                    //bridges
                    ret.addAll( JARLevelObjects.createBridge( 1536, iLevelHeight - 64, 1 ) );
                    ret.addAll( JARLevelObjects.createBridge( 3200, iLevelHeight - 64, 2 ) );
                    ret.addAll( JARLevelObjects.createBridge( 6256, iLevelHeight - 64, 3 ) );

                    //grass
                    ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 64, 12, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 1920, iLevelHeight - 64, 10, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 3712, iLevelHeight - 64, 20, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 6896, iLevelHeight - 64, 20, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );

                    //signposts
                    ret.add(    new JARWall( 475,   iLevelHeight - 212,  JARImage.EDecoSignpost1,    CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );
                    ret.add(    new JARWall( 2000,  iLevelHeight - 212,  JARImage.EDecoSignpost1,    CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );
                    ret.add(    new JARWall( 3750,  iLevelHeight - 212,  JARImage.EDecoSignpost1,    CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );
                    //trees
                    ret.add(    new JARWall( 675,   iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );
                    ret.add(    new JARWall( 2300,  iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );
                    ret.add(    new JARWall( 4250,  iLevelHeight - 312,  JARImage.EDecoTree1,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );

                    //steps
                    ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 192,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 128,  iLevelHeight - 320,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 256,  iLevelHeight - 448,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 384,  iLevelHeight - 576,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 512,  iLevelHeight - 704,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 640,  iLevelHeight - 832,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 768,  iLevelHeight - 960,  1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 896,  iLevelHeight - 1088, 1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 1024, iLevelHeight - 1216, 1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 1152, iLevelHeight - 1344, 1, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );

                    break;
                }

                case EForestFrenzy:
                {
                    //block
                    ret.addAll( JARLevelObjects.createBlock(  128, iLevelHeight - 256, 6, 2, JARImage.EWallStone1, CollisionType.EColliding ) );
/*
                    //grass
                    ret.addAll( JARLevelObjects.createFloor( 0,     iLevelHeight - 64,  2, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 896,   iLevelHeight - 384, 4, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 2048,  iLevelHeight - 64,  4, JARImage.EWallGrass1, JARImage.EWallGrass1Topping ) );

                    //stairs
                    ret.addAll( JARLevelObjects.createElevation( 256,  iLevelHeight - 128, 5, Elevation.EAscending,  JARImage.EWallGrass1Ascending,  JARImage.EWallGrass1ToppingAscending  ) );
                    ret.addAll( JARLevelObjects.createElevation( 1408, iLevelHeight - 384, 5, Elevation.EDescending, JARImage.EWallGrass1Descending, JARImage.EWallGrass1ToppingDescending ) );
*/
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

                default:
                {
                    //floor 'water'
                    //ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 64, 12, JARImage.EGrass1, JARImage.EGrass1Topping ) );
                    ret.addAll( JARLevelObjects.createFloor( 0,    iLevelHeight - 64, 15, JARImage.EWallWater1, null ) );

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
/*
                        new JARPlayer( 3000, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyStrong, StandingDirection.ELeft     ),
                        new JARPlayer( 500,  -1, GameObjectType.EEnemy, JARPlayerType.EEnemyWeak,   StandingDirection.ELeft     ),
                        new JARPlayer( 1000, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyWeak,   StandingDirection.ELeft     ),
                        new JARPlayer( 1500, -1, GameObjectType.EEnemy, JARPlayerType.EEnemyWeak,   StandingDirection.ELeft     ),
*/
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

                default:
                {
                    ret = new JARPlayer[]
                    {
                        new JARPlayer( 500, 0, GameObjectType.EEnemy, JARPlayerType.EEnemyStrong, StandingDirection.ELeft   ),
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

                        new JARItem( 1172,  0,                      JARItemType.ECoin,          true    ),
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
