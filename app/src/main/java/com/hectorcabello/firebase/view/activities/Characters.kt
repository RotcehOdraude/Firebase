package com.hectorcabello.firebase.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.hectorcabello.firebase.databinding.ActivityCharactersBinding
import com.hectorcabello.firebase.model.StaffHP
import com.hectorcabello.firebase.model.StudentHP
import com.hectorcabello.firebase.network.HPApi
import com.hectorcabello.firebase.network.RetrofitService
//import com.example.harrypotter.utils.Constants
import com.hectorcabello.firebase.view.adapters.StaffAdapter
import com.hectorcabello.firebase.view.adapters.StudentsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Characters : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val seleccion = bundle?.getString("seleccion", "")

        //Log.d(Constants.LOGTAG, "Respuesta del bundle: ${seleccion}")

        if (seleccion.equals("Student")) {
            //Toast.makeText(this, "Seleccion ${seleccion}", Toast.LENGTH_SHORT).show()
            val call = RetrofitService.getRetrofit().create(HPApi::class.java)
                .getStudents("api/characters/students")
            //Log.d(Constants.LOGTAG, "Respuesta del call: ${call}")

            call.enqueue(object : Callback<ArrayList<StudentHP>> {
                override fun onResponse(
                    call: Call<ArrayList<StudentHP>>,
                    response: Response<ArrayList<StudentHP>>
                ) {
                    binding.shimmerViewContainer.isVisible = false
                    //Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    //Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                    binding.rvMenu.layoutManager = LinearLayoutManager(this@Characters)
                    binding.rvMenu.adapter = StudentsAdapter(
                        this@Characters,
                        response.body()!!
                    ) { selectedStudentHP: StudentHP ->
                        studentClicked(selectedStudentHP)
                    }

                }

                override fun onFailure(call: Call<ArrayList<StudentHP>>, t: Throwable) {
                    binding.shimmerViewContainer.isVisible = false
                    Toast.makeText(this@Characters, "No hay conexión", Toast.LENGTH_SHORT).show()
                }

            })
        } else if (seleccion.equals("Staff")) {
            //Toast.makeText(this, "Seleccion ${seleccion}", Toast.LENGTH_SHORT).show()

            val call = RetrofitService.getRetrofit().create(HPApi::class.java)
                .getStaff("api/characters/staff")
            //Log.d(Constants.LOGTAG, "Respuesta del call: $call")

            call.enqueue(object : Callback<ArrayList<StaffHP>> {
                override fun onResponse(
                    call: Call<ArrayList<StaffHP>>,
                    response: Response<ArrayList<StaffHP>>
                ) {
                    binding.shimmerViewContainer.isVisible = false
                    //Log.d(Constants.LOGTAG, "Respuesta del servidor: $response")

                    //Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                    binding.rvMenu.layoutManager = LinearLayoutManager(this@Characters)
                    binding.rvMenu.adapter = StaffAdapter(
                        this@Characters,
                        response.body()!!
                    ) { selectedStaffHP: StaffHP ->
                        staffClicked(selectedStaffHP)
                    }

                }

                override fun onFailure(call: Call<ArrayList<StaffHP>>, t: Throwable) {
                    binding.shimmerViewContainer.isVisible = false
                    Toast.makeText(this@Characters, "No hay conexión", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun studentClicked(student: StudentHP) {
        //Toast.makeText(this, "Clic a ${student.actor}", Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putString("id", student.id)
        val intent = Intent(this, Details::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun staffClicked(staff: StaffHP) {
        //Toast.makeText(this, "Clic en el elemento con títiulo ${staff.actor}", Toast.LENGTH_SHORT.show()
        val bundle = Bundle()
        bundle.putString("id", staff.id)
        val intent = Intent(this, Details::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}