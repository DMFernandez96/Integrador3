package Integrador3.Comparator;

import Integrador3.DTO.CarreraDTO;

import java.util.Comparator;

public class ComparadorDTO implements Comparator<CarreraDTO> {


	@Override
	public int compare(CarreraDTO o1, CarreraDTO o2) {
		return (o1.getAnio()>o2.getAnio()) ? 1:-1;
	}
} 
