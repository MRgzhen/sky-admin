package com.yanyu.sky.generator.util;

import com.yanyu.sky.generator.bean.po.TemplateDesc;
import com.yanyu.sky.generator.dao.TemplateDescMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.apache.velocity.util.ExceptionUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author yanyu
 * @date 2020/12/1
 */
@Slf4j
@AllArgsConstructor
public class DbResourceLoader extends ResourceLoader {
    private TemplateDescMapper mapper;
    @Override
    public void init(ExtendedProperties extendedProperties) {
        if (log.isTraceEnabled()) {
            log.trace("DbResourceLoader : initialization complete.");
        }
    }

    @Override
    public InputStream getResourceStream(String name) throws ResourceNotFoundException {
        InputStream result = null;
        if (StringUtils.isEmpty(name)) {
            throw new ResourceNotFoundException("No template name provided");
        } else {
            try {
                TemplateDesc templateDes = mapper.getById(name);
                if(templateDes != null) {
                    result = new ByteArrayInputStream(templateDes.getContent().getBytes(Charset.defaultCharset()));
                }
            } catch (Exception var4) {
                log.error("",var4);
                throw (ResourceNotFoundException) ExceptionUtils.createWithCause(ResourceNotFoundException.class, "problem with template: " + name, var4);
            }

            if (result == null) {
                String msg = "ClasspathResourceLoader Error: cannot find resource " + name;
                throw new ResourceNotFoundException(msg);
            } else {
                return result;
            }
        }
    }

    @Override
    public boolean isSourceModified(Resource resource) {
        return false;
    }

    @Override
    public long getLastModified(Resource resource) {
        return 0;
    }
}
