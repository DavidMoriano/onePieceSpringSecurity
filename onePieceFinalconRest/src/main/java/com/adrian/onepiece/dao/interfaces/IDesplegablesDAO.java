package com.adrian.onepiece.dao.interfaces;

import java.util.ArrayList;

import com.adrian.onepiece.dtos.DesplegableDTO;

public interface IDesplegablesDAO {

    ArrayList<DesplegableDTO> desplegableIslas();

    ArrayList<DesplegableDTO> desplegablePiratasActivos();

    ArrayList<DesplegableDTO> desplegableTripulacionesActivas();
}
