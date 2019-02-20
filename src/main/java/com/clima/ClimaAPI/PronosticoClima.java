package com.clima.ClimaAPI;

import java.util.List;

import org.bson.types.ObjectId;

import com.clima.ClimaAPI.models.ClimaAPI;
import com.clima.ClimaAPI.models.Planeta;
import com.clima.ClimaAPI.repositories.ClimaAPIRepository;
import com.clima.Utils.Punto;
import com.clima.Utils.Recta;

public class PronosticoClima {

	private ClimaAPIRepository climaRepository = null;
	private static PronosticoClima instancia = null;
	private static final String SENTIDO_HORARIO = "horario";
	private static final String PLANETAS_ALINEADOS = "planetas-alineados";
	private static final String SOL_PLANETAS_ALINEADOS = "sol-planetas-alineados";
	private static final String NO_ALINEADOS = "no-alineados";

	public PronosticoClima(ClimaAPIRepository climaRepository) {
		this.climaRepository = climaRepository;
	}

	public static PronosticoClima getInstancia(ClimaAPIRepository climaRepository) {
		if (instancia == null) {
			instancia = new PronosticoClima(climaRepository);
		}
		return instancia;
	}

	public String pronosticoClima(int dias, List<Planeta> planetas) throws Exception {
		ClimaAPI climaModel = climaRepository.findByDias(dias);
		if (climaModel == null) {
			String clima = calcularPronostico(dias, planetas);
			climaModel = new ClimaAPI(dias, clima);
			climaModel.set_id(ObjectId.get());
			climaRepository.save(climaModel);
		}
		return climaModel.toString();
	}

	private String calcularPronostico(int dias, List<Planeta> planetas) throws Exception {
		String tipoAlineamiento =  getTipoAlineamiento(planetas, dias);
		switch (tipoAlineamiento) {
			case PLANETAS_ALINEADOS:
				return "Condición óptima";
			case SOL_PLANETAS_ALINEADOS:
				return "Sequía";
			default:
				return "Lluvioso";
		}
	}

	private String getTipoAlineamiento(List<Planeta> planetas, int dias) throws Exception {
		Planeta ferengi = getPlaneta("Ferengi", planetas);
		Planeta betasoide = getPlaneta("Betasoide", planetas);
		Planeta vulcano = getPlaneta("Vulcano", planetas);
		double anguloPendienteFerengi = calcularAnguloPendiente(dias, ferengi);
		int pendienteFerengi = (int) Math.tan(anguloPendienteFerengi);
		int pendienteBetasoide = (int) Math.tan(calcularAnguloPendiente(dias, betasoide));
		int pendienteVulcano = (int) Math.tan(calcularAnguloPendiente(dias, vulcano));
		String alineados;
		if (pendienteFerengi == pendienteBetasoide && pendienteBetasoide == pendienteVulcano) {
			alineados = PLANETAS_ALINEADOS;
			if (solPlanetasAlineados(anguloPendienteFerengi)) {
				alineados = SOL_PLANETAS_ALINEADOS;
			}
		} else {
			alineados = NO_ALINEADOS;
		}
		return alineados;
	}

	private double calcularAnguloPendiente(int dias, Planeta planeta) {
		double angulo = planeta.getSentido().equals(SENTIDO_HORARIO) ? 360 - (dias * planeta.getVelocidadAngular())
				: dias * planeta.getVelocidadAngular();
		return Math.toRadians(Math.abs((angulo + 360) % 360));
	}

	private boolean solPlanetasAlineados(double angulo) {
		angulo = Math.toDegrees(angulo);
		return angulo == 90 || angulo == 0 || angulo == 180 || angulo == 270 || angulo == 360;
	}

	private Planeta getPlaneta(String nombrePlaneta, List<Planeta> planetas) throws Exception {
		return planetas.stream()
				.filter(planeta -> nombrePlaneta.equals(planeta.getNombre()))
				.findAny()
				.orElseThrow(() -> new Exception("Planeta no encontrado."));
	}
}
