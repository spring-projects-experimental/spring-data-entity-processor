package org.springframework.data.entity.processor.writer;

import java.io.File;
import java.io.IOException;

import org.springframework.data.entity.processor.model.DomainTypes;

/**
 * @author Christoph Strobl
 * @since 2020/11
 */
public interface GraalVmConfigurationWriter {

	void writeGraalVmConfiguration(DomainTypes domainTypes, File targetDirectory) throws IOException;
}
