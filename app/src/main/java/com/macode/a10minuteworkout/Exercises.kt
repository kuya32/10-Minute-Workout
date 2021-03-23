package com.macode.a10minuteworkout

class Exercises {
    companion object {
        fun defaultExerciseList(): ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val highKnees = ExerciseModel(
                1,
                "High Knees",
                R.drawable.high_knees,
                false,
                false
            )

            val sideLunges = ExerciseModel(
                2,
                "Alternating Side Lunges",
                R.drawable.side_lunge,
                false,
                false
            )

            val lunges = ExerciseModel(
                3,
                "Alternating Lunges",
                R.drawable.lunges,
                false,
                false
            )

            val gobletSquat = ExerciseModel(
                4,
                "Goblet Squat",
                R.drawable.goblet_squat,
                false,
                false
            )

            val wallSit = ExerciseModel(
                5,
                "Wall Sit",
                R.drawable.ic_wall_sit,
                false,
                false
            )

            val hydrantLeft = ExerciseModel(
                6,
                "Hydrant - Left",
                R.drawable.hydrants,
                false,
                false
            )
            val hydrantRight = ExerciseModel(
                7,
                "Hydrant - Right",
                R.drawable.hydrants,
                false,
                false
            )


            val plank = ExerciseModel(
                8,
                "Plank",
                R.drawable.plank,
                false,
                false
            )

            val supermans = ExerciseModel(
                9,
                "Supermans",
                R.drawable.supermans,
                false,
                false
            )

            val mountainClimbers = ExerciseModel(
                10,
                "Mountain Climbers",
                R.drawable.mountain_climbers,
                false,
                false
            )

            val backLegLiftLeft = ExerciseModel(
                11,
                "Plank Single Back Leg Lift - Left",
                R.drawable.back_leg_lift,
                false,
                false
            )

            val backLegLiftRight = ExerciseModel(
                12,
                "Plank Single Back Leg Lift - Right",
                R.drawable.back_leg_lift,
                false,
                false
            )

            val abWheel = ExerciseModel(
                13,
                "Ab Wheel",
                R.drawable.ab_wheel,
                false,
                false
            )

            val crabToe = ExerciseModel(
                14,
                "Crab Toe Touch",
                R.drawable.crab_toe_touches,
                false,
                false
            )

            val bicycle = ExerciseModel(
                15,
                "Bicycle Crunches",
                R.drawable.bicycle_crunch,
                false,
                false
            )

            val bridge = ExerciseModel(
                16,
                "Bridges",
                R.drawable.bridge,
                false,
                false
            )

            val jackKnife = ExerciseModel(
                17,
                "Jack Knife Crunches",
                R.drawable.jacknife_toe_touches,
                false,
                false
            )

            val singleLegBridgeLeft = ExerciseModel(
                18,
                "Single Leg Bridge - Left",
                R.drawable.single_leg_bridge,
                false,
                false
            )

            val singleLegBridgeRight = ExerciseModel(
                19,
                "Single Leg Bridge - Right",
                R.drawable.single_leg_bridge,
                false,
                false
            )

            val russianTwists = ExerciseModel(
                20,
                "Russian Twists",
                R.drawable.russian_twist,
                false,
                false
            )

            exerciseList.add(highKnees)
            exerciseList.add(sideLunges)
            exerciseList.add(lunges)
            exerciseList.add(gobletSquat)
            exerciseList.add(wallSit)
            exerciseList.add(hydrantLeft)
            exerciseList.add(hydrantRight)
            exerciseList.add(plank)
            exerciseList.add(supermans)
            exerciseList.add(mountainClimbers)
            exerciseList.add(backLegLiftLeft)
            exerciseList.add(backLegLiftRight)
            exerciseList.add(abWheel)
            exerciseList.add(crabToe)
            exerciseList.add(bicycle)
            exerciseList.add(bridge)
            exerciseList.add(jackKnife)
            exerciseList.add(singleLegBridgeLeft)
            exerciseList.add(singleLegBridgeRight)
            exerciseList.add(russianTwists)

            return exerciseList
        }
    }
}