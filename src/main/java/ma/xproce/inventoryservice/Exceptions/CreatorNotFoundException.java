package ma.xproce.inventoryservice.Exceptions;

public class CreatorNotFoundException extends RuntimeException{
    public CreatorNotFoundException(Long id) {
        super(String.format("Creator %s not found", id)); // Custom message
    }
}
