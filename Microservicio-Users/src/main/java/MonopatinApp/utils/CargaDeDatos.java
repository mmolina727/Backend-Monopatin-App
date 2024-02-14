package MonopatinApp.utils;

import MonopatinApp.entities.Cuenta;
import MonopatinApp.entities.Usuario;
import MonopatinApp.repositories.CuentaRepository;
import MonopatinApp.repositories.UsuarioRepository;
import MonopatinApp.services.CuentaService;
import MonopatinApp.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class CargaDeDatos {
    @Autowired
   private final CuentaRepository cuentaRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    
    public CargaDeDatos(CuentaRepository n,UsuarioRepository u )
    {
        this.usuarioRepository = u;
        this.cuentaRepository = n;
    }
    @PostConstruct
    public void readCsvFile() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/cuenta.csv");

            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : csvParser) {
                String column1 = record.get("saldo");
                String column2 = record.get("fechaAlta");
                

               Cuenta c = new Cuenta(Double.parseDouble(column1),LocalDate.parse(column2));
                cuentaRepository.save(c);
            }

            FileReader fileReaderUs = new FileReader("src/main/resources/usuarios.csv");
            CSVParser csvParserUs = new CSVParser(fileReaderUs, CSVFormat.DEFAULT.withHeader());
            for(CSVRecord r:csvParserUs){
                String n = r.get("nombre");
                String a = r.get("apellido");
                String e = r.get("mail");
                int num = Integer.parseInt(r.get("numTelefono"));
                Optional<Cuenta> c = cuentaRepository.findById(Integer.parseInt(r.get("idCuenta")));

                Usuario aux = new Usuario(n,a,e,num);
                if(c.isPresent()){
                    aux.agregarCuenta(c.get());
                }
                usuarioRepository.save(aux);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
