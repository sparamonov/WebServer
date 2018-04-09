package templater;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import practice.CreatedBy;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class PageGenerator {
    private static final String HTML_DIR = "templates";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            cfg.setDirectoryForTemplateLoading(new File(HTML_DIR));
            Template template = cfg.getTemplate(filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator() {cfg = new Configuration(Configuration.VERSION_2_3_25);}
}
