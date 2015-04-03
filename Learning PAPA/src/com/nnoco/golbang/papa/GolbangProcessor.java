package com.nnoco.golbang.papa;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes(value={"*"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class GolbangProcessor extends AbstractProcessor{
	
	private Filer filer;
	private Messager messager;

	@Override
	public void init(ProcessingEnvironment env) {
		super.init(env);
		filer = env.getFiler();
		messager = env.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> elements,
			RoundEnvironment env) {
		for (Element element : env.getRootElements()) {
			if (element.getSimpleName().toString().startsWith("Golbang")) {
				continue;
			}
			
			if (element.getSimpleName().toString().startsWith("Api")) {
				messager.printMessage(Kind.WARNING, "This class name starts with a Api", element);
			}
			
			String golbangClassName = "Golbang" + element.getSimpleName();
			String golbangClassContent = 
					"package com.nnoco.golbang.client;\n" 
					+ "public class " + golbangClassName + " {\n"
					+ "	public String boobar;\n"
					+ "}";
			
			JavaFileObject file = null;
			
			try {
				file = filer.createSourceFile("com/nnoco/golbang/client/" + golbangClassName, element);
				file.openWriter()
				.append(golbangClassContent)
				.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(TypeElement element : elements) {
			System.out.println(element.getQualifiedName());
		}
		return true;
	}
	
}
