package app.dao.ex;

import app.datamodel.ax.MSInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@RepositoryRestResource(path = "ms")
public interface MSDao extends JpaRepository<MSInfo,Integer> {
}
