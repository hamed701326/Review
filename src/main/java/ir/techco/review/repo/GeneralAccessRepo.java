package ir.techco.review.repo;

import ir.techco.review.repo.document.GeneralAccess;
import ir.techco.review.repo.extra.GeneralAccessRepoExtra;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeneralAccessRepo extends MongoRepository<GeneralAccess,String>, GeneralAccessRepoExtra {

    GeneralAccess findGeneralAccessByProfile(String profile);


}
