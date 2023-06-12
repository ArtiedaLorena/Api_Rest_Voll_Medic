package med.voll.api.paciente;

public record DatosListaPaciente(long id, String nombre, String email, String documentoIdentidad) {
    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}