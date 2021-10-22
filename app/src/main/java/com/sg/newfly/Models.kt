package com.sg.newfly

sealed class Models {

    abstract val degreesPerSecond: Float
    abstract val radius: Float
    abstract val height: Float
    abstract val rotationDegrees: Float

    object Rumba2 : Models() {
        override val degreesPerSecond: Float
            get() = 10f
        override val radius: Float
            get() = 0.03f
        override val height: Float
            get() = 0.0f
        override val rotationDegrees: Float
            get() = 180f
    }



    object Rumba : Models() {
        override val degreesPerSecond: Float
            get() = 20f
        override val radius: Float
            get() = 0.3f
        override val height: Float
            get() = 0.0f
        override val rotationDegrees: Float
            get() = 180f
    }


    object Fly : Models() {
        override val degreesPerSecond: Float
            get() = 20f
        override val radius: Float
            get() = 2f
        override val height: Float
            get() = 0.7f
        override val rotationDegrees: Float
            get() = 180f
    }
    object Bird : Models() {
        override val degreesPerSecond: Float
            get() = 20f
        override val radius: Float
            get() = 2f
        override val height: Float
            get() = 0.7f
        override val rotationDegrees: Float
            get() = 180f
    }
    object Fish : Models() {
        override val degreesPerSecond: Float
            get() = 20f
        override val radius: Float
            get() = 2f
        override val height: Float
            get() = 0.7f
        override val rotationDegrees: Float
            get() = 180f
    }

    object Bee : Models() {
        override val degreesPerSecond: Float
            get() = 20f
        override val radius: Float
            get() = 0.3f
        override val height: Float
            get() = 0.1f
        override val rotationDegrees: Float
            get() = 180f
    }
    object Jet : Models() {
        override val degreesPerSecond: Float
            get() = 20f
        override val radius: Float
            get() = 2f
        override val height: Float
            get() = 0.7f
        override val rotationDegrees: Float
            get() = 180f
    }


}