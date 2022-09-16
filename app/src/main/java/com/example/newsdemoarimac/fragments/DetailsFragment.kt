package com.example.newsdemoarimac.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import com.example.newsdemoarimac.R
import com.example.newsdemoarimac.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment(),View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.ibBack.setOnClickListener(this)

        //Blur Center view
        val rootView = binding.llBlurView
        rootView.post{
            rootView.setBackgroundDrawable(
                BitmapDrawable(
                    resources, blur(
                        context,
                        captureScreenShot(rootView)!!
                    )
                )
            )

            //Blur back button view
            binding.ibBack.setBackgroundDrawable(
                BitmapDrawable(
                    resources, blur(
                        context,
                        captureScreenShot(binding.ibBack)!!
                    )
                )
            )
        }


    }

    fun captureScreenShot(view: View): Bitmap? {
/*
 * Creating a Bitmap of view with ARGB_4444.
 * */
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_4444)
        val canvas = Canvas(bitmap)
        val backgroundDrawable = view.background
        if (backgroundDrawable != null) {
            backgroundDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.parseColor("#80000000"))
        }
        view.draw(canvas)
        return bitmap
    }

    fun blur(context: Context?, image: Bitmap): Bitmap? {
        val BITMAP_SCALE = 1f
        val BLUR_RADIUS = 1f
        val width = Math.round(image.width * BITMAP_SCALE)
        val height = Math.round(image.height * BITMAP_SCALE)
        val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)
        val rs: RenderScript = RenderScript.create(context)
        val theIntrinsic: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn: Allocation = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut: Allocation = Allocation.createFromBitmap(rs, outputBitmap)
        theIntrinsic.setRadius(BLUR_RADIUS)
        theIntrinsic.setInput(tmpIn)
        theIntrinsic.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)
        return outputBitmap
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailsFragment()
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.ib_back -> {
                navController.popBackStack()
            }
        }
    }
}