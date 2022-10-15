package Integrador3.Utils;


import Integrador3.Controller.CarreraController;

import Integrador3.Controller.MatriculacionController;

import Integrador3.Model.Carrera;
import Integrador3.Model.Estudiante;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
    CommandLineRunner initDatabase(CarreraController ctrlCarrera, MatriculacionController ctrlMatriculacion) {
        return args -> {
            this.cargarEstudiantes(this.leerCarreras(ctrlCarrera), ctrlMatriculacion);
        };
    }

    public void cargarEstudiantes(List<Carrera> carreras, MatriculacionController ctrlMatriculacion) {
        try {
            @SuppressWarnings("deprecation")
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/java/integrador3/assets/estudiantes100.csv"));
            System.out.println("Lista de carreras cargadas.");
            for(CSVRecord row: parser) {
                Estudiante tmp = new Estudiante(parseInt(row.get("dni")),parseInt(row.get("nrolibreta")),row.get("nombre"),row.get("apellido"),parseInt(row.get("edad")),generarGenero(),row.get("ciudad"));
                ctrlMatriculacion.crearMatriculacion(carreras.get((int) (Math.random()*19+1)), tmp);
            }
            System.out.println("Estudiantes enlazados.");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Carrera> leerCarreras(CarreraController ctrlCarrera){
        List<Carrera> car = new ArrayList<>();
        try {
            @SuppressWarnings("deprecation")
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/java/integrador3/assets/carreras20.csv"));
            for(CSVRecord row: parser) {
                Carrera tmp = new Carrera(row.get("nombre"),parseInt(row.get("duracion")));
                car.add(tmp);
            }
            ctrlCarrera.guardarCarreras(car);
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
