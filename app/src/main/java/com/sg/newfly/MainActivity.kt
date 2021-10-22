package com.sg.newfly

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.scaleMatrix
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.animation.ModelAnimator
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var arFragment: ArFragment
    private val nodes = mutableListOf<RotatingNode>()

   /* private val model = Models.Bee
    private val modelResourceId=R.raw.beedrill*/

    /*private val model = Models.Rumba
    private val modelResourceId=R.raw.rumba*/

    private val model = Models.Rumba2
    private val modelResourceId=R.raw.rumba2

    private var curCameraPosition=Vector3.zero()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arFragment=fragment as ArFragment
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            loadModelAndAddToSence(hitResult.createAnchor(),modelResourceId)
        }
    }

    private fun loadModelAndAddToSence(anchor: Anchor?, modelResourceId: Int) {
        ModelRenderable.builder()
            .setSource(this,modelResourceId)
            .build()
            .thenAccept { modelRenderable->
                addNodeToScene(anchor,modelRenderable)
                eliminateDot()
            }.exceptionally {
                Toast.makeText(this,"Error creatind nodes:$it",Toast.LENGTH_LONG).show()
                null
            }

    }

    private fun addNodeToScene(
        anchor: Anchor?,
        modelRenderable: ModelRenderable?
    ) {
        val anchorNode=AnchorNode(anchor)
        val rotatingNode=RotatingNode(model.degreesPerSecond).apply {

             setParent(anchorNode)
        }
        Node().apply {
            renderable=modelRenderable

            setParent(rotatingNode)
            localPosition= Vector3(model.radius,model.height,0f)
            localRotation= Quaternion.eulerAngles(Vector3(0f,model.rotationDegrees,0f))
           localScale=Vector3(0.05f,0.05f,0.05f)
        }
      arFragment.arSceneView.scene.addChild(anchorNode)
      nodes.add(rotatingNode)
     //val animationData=modelRenderable?.getAnimationData("Beedrill_Animation")
     // val animationData=modelRenderable?.getAnimationData("mixamo.com")
      val animationData=modelRenderable?.getAnimationData("Cinema_4D_Basis")
      ModelAnimator(animationData,modelRenderable).apply {
          repeatCount=ModelAnimator.INFINITE
          start()
      }
    }

    private fun eliminateDot() {
        arFragment.arSceneView.planeRenderer.isVisible = false
        arFragment.planeDiscoveryController.hide()
        arFragment.planeDiscoveryController.setInstructionView(null)
    }


   /* private fun spawnObject(anchor: Anchor, modelUri: Uri) {
        val renderableSource = RenderableSource.builder()
            .setSource(this, modelUri, RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
            //.setScale(0.002f)  //for the Bee
            .setScale(0.02f)
            .build()
        ModelRenderable.builder()
            .setSource(this, renderableSource)
            .setRegistryId(modelUri)
            .build()
            .thenAccept {
                addNodeToScene(anchor, it)
            }.exceptionally {
                Toast.makeText(this, "Somting go wrong: $it", Toast.LENGTH_LONG).show()
                null
            }

    }*/



}

//Original versia of fly
/*  private val spaceship = Models.Bee
  private var modelResourceId = R.raw.beedrill

  private val nodes = mutableListOf<RotatingNode>()
  private lateinit var arFragment: ArFragment
  private var curCameraPosition = Vector3.zero()

  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
      arFragment = fragment as ArFragment
      arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
          loadModelAndAddToScene(hitResult.createAnchor(), modelResourceId)
      }
      arFragment.arSceneView.scene.addOnUpdateListener {
          updateNodes()
      }
      setupFab()
  }

  private fun loadModelAndAddToScene(anchor: Anchor, modelResourceId: Int) {
      ModelRenderable.builder()
          .setSource(this, modelResourceId)
          .build()
          .thenAccept { modelRenderable ->
              addNodeToScene(anchor, modelRenderable, spaceship)
              eliminateDot()
          }.exceptionally {
              Toast.makeText(this, "Error creating node: $it", Toast.LENGTH_LONG).show()
              null
          }

  }

  private fun updateNodes() {
      curCameraPosition = arFragment.arSceneView.scene.camera.worldPosition
      for (node in nodes) {
          node.worldPosition =
              Vector3(curCameraPosition.x, node.worldPosition.y, curCameraPosition.z)
      }
  }

  private fun addNodeToScene(
      anchor: Anchor,
      modelRenderable: ModelRenderable,
      spaceship: Models
  ) {
      val anchorNode = AnchorNode(anchor)
      val rotatingNode = RotatingNode(spaceship.degreesPerSecond).apply {
          setParent(anchorNode)
      }
      Node().apply {
          renderable = modelRenderable
          setParent(rotatingNode)
          localPosition = Vector3(spaceship.radius, spaceship.height, 0f)
          localRotation = Quaternion.eulerAngles(Vector3(0f, spaceship.rotationDegrees, 0f))
      }
      arFragment.arSceneView.scene.addChild(anchorNode)
      nodes.add(rotatingNode)
      val animationData = modelRenderable.getAnimationData("Beedrill_Animation")
      ModelAnimator(animationData, modelRenderable).apply {
          repeatCount = ModelAnimator.INFINITE
          start()
      }

  }

  private fun addNodeToScene(anchor: Anchor, modelRenderable: ModelRenderable) {
      val anchorNode = AnchorNode(anchor)
      TransformableNode(arFragment.transformationSystem).apply {
          renderable = modelRenderable
          setParent(anchorNode)
      }
      arFragment.arSceneView.scene.addChild(anchorNode)
  }

  private fun setupFab() {
      *//*      photoSaver = PhotoSaver(this)
              videoRecorder = VideoRecorder(this).apply {
                  sceneView = arFragment.arSceneView

                  setVideoQuality(CamcorderProfile.QUALITY_1080P, resources.configuration.orientation)
              }
              fab.setOnClickListener {
                  if (!isRecording) {
                      photoSaver.takePhoto(arFragment.arSceneView)
                  }
              }

              fab.setOnLongClickListener {
                  isRecording = videoRecorder.toggleRecordingState()
                  true
              }

              fab.setOnTouchListener { view, motionEvent ->

                  if (motionEvent.action == MotionEvent.ACTION_UP && isRecording) {

                      isRecording = videoRecorder.toggleRecordingState()

                      Toast.makeText(this, "Saved video to gallery!", Toast.LENGTH_LONG).show()

                      true

                  } else false

              }*//*

    }



private fun eliminateDot() {
    arFragment.arSceneView.planeRenderer.isVisible = false
    arFragment.planeDiscoveryController.hide()
    arFragment.planeDiscoveryController.setInstructionView(null)
}
}

        */