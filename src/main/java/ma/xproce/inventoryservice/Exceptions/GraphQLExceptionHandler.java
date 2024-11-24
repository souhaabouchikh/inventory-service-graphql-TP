package ma.xproce.inventoryservice.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import ma.xproce.inventoryservice.Exceptions.CreatorNotFoundException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        // Handle specific CreatorNotFoundException
        if (ex instanceof CreatorNotFoundException) {
            return new GraphQLError() {

                @Override
                public String getMessage() {
                    return ex.getMessage(); // Return the custom message from CreatorNotFoundException
                }

                @Override
                public List<SourceLocation> getLocations() {
                    // Optional: If needed, return source locations where the error occurred (null if not needed)
                    return env.getField().getSourceLocation() != null
                            ? List.of(env.getField().getSourceLocation())
                            : null;
                }

                @Override
                public ErrorClassification getErrorType() {
                    // Return the appropriate error type for this exception
                    return null;
//                    return ErrorClassification.DataFetchingException; // Data fetching related error
                }
            };
        }

        // Optional: Handle other exceptions, if needed
        return super.resolveToSingleError(ex, env);
    }

}
