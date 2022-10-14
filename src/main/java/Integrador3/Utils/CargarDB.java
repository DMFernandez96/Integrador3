package Integrador3.Utils;


import Integrador3.Model.Matriculacion;
import Integrador3.Model.Carrera;
import Integrador3.Model.Estudiante;
import Integrador3.Repository.CarreraRepository;
import Integrador3.Servicios.ServicioCarreraImpl;
import Integrador3.Servicios.ServicioEstudianteImpl;
import Integrador3.Servicios.ServicioMatriculacionImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Configuration
@Slf4j
public class CargarDB {

    @Bean
    CommandLineRunner initDatabase(ServicioCarreraImpl sc, ServicioEstudianteImpl se, ServicioMatriculacionImpl sm ) {
        return args -> {
//            log.info("Cargue la carrera 1"+ cr.save(new Carrera("Tudai",5)));
//            cargarEstudiantes(se,sm,sc);
            
            Carrera c1 = new Carrera("TUDAI", 3);
        	Carrera c2 = new Carrera("Contador", 5);
        	Estudiante e1 = new Estudiante(123456,12345,"Harry", "Styles", 28, 'm', "Necochea" );
        	Estudiante e2 = new Estudiante(654321,54321,"Alejandro", "Lerner", 60, 'm', "Tandil" );
//        	Matriculacion m1 = new Matriculacion(e1,c1,2015,2010);
            sc.insertarCarrera(c1);
            sc.insertarCarrera(c2);
            se.insertarEstudiante(e1);
            se.insertarEstudiante(e2);
            
            //revisar creacion de matriculaciones
//          Matriculacion m1= sm.crearMatriculacion(e1,c1,2015,2010);   
//	       	sm.insertarMatriculacion(m1);
//	       	e1.agregarMatriculacion(m1);
//	       	c1.agregarMatriculacion(m1);
            System.out.println(sc.listarCarreras());
        };
    }

    public void cargarEstudiantes(ServicioEstudianteImpl svcEstudiante, ServicioMatriculacionImpl svcMatriculacion, ServicioCarreraImpl svcCarrera) {
        List<Carrera> carreras = leerCarreras();
        try {
            @SuppressWarnings("deprecation")
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/java/com/example/demo/assets/estudiantes100.csv"));
            System.out.println("Estoy cargando los estudiantes...");
            for(CSVRecord row: parser) {
                Estudiante tmp = new Estudiante(parseInt(row.get("dni")),parseInt(row.get("nrolibreta")),row.get("nombre"),row.get("apellido"),parseInt(row.get("edad")),generarGenero(),row.get("ciudad"));
                altaEstudiante(tmp, carreras.get((int) (Math.random()*20+1)),svcEstudiante,svcMatriculacion,svcCarrera);
            }
            System.out.println("No se me da nada mal");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Carrera> leerCarreras(){
        List<Carrera> car = new ArrayList<>();
        try {
            @SuppressWarnings("deprecation")
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/java/com/example/demo/assets/carreras20.csv"));
            System.out.println("Estoy cargando las carreras...");
            for(CSVRecord row: parser) {
                Carrera tmp = new Carrera(row.get("nombre"),parseInt(row.get("duracion")));
                car.add(tmp);
            }
            System.out.println("No se me da nada mal");
            return car;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return car;
    }

    public boolean altaEstudiante(@RequestBody Estudiante e, @RequestBody Carrera c,ServicioEstudianteImpl svcEstudiante, ServicioMatriculacionImpl svcMatriculacion , ServicioCarreraImpl svcCarrera) {
        if(e != null && c != null) {
            Random random = new Random();
            //40% chance of true
            boolean chances40true = (random.nextInt(5) < 2) ? true : false;
            int anioRandomIngreso=0;//null
            int anioRandomEgreso = 0;
            int duracionTemp= c.getDuracion();

            if(chances40true) {
                anioRandomIngreso= generateRandomInt(2010, 2022-duracionTemp);
                anioRandomEgreso= generateRandomInt(2022-duracionTemp, 2022);
            }else {
                anioRandomIngreso=generateRandomInt(2010, 2022);
            }

//			Matriculacion mat = new Matriculacion(e,c,anioRandomEgreso,anioRandomIngreso);
            Matriculacion mat = svcMatriculacion.crearMatriculacion(e,c,anioRandomEgreso,anioRandomIngreso);

//			if(svcCarrera.actualizarCarrera(c) && svcEstudiante.insertarEstudiante(e)) {
//            svcCarrera.actualizarCarrera(c);
            svcEstudiante.insertarEstudiante(e);
            svcMatriculacion.insertarMatriculacion(mat);
//				return true;
//			}
        }
        return false;
    }

    public int generateRandomInt(int min, int max){
        return (int) Math.floor((Math.random() * (max+1 -min)) +min);}

    public char generarGenero() {
        Random random = new Random();
        //50% chance of true
        boolean chances50true = (random.nextInt(4) < 2) ? true : false;
        if(chances50true) {
            return 'f';
        }
        return 'm';
    }
}
