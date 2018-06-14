package com.androidfinder.compiler;

import com.androidfinder.FindColor;
import com.androidfinder.FindDrawable;
import com.androidfinder.FindString;
import com.androidfinder.FindView;
import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class AndroidFinderProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Map<TypeElement, FindFieldContainer> map = parserAnnotation(roundEnvironment);
        return true;
    }

    private Map<TypeElement, FindFieldContainer> parserAnnotation(RoundEnvironment env) {
        Map<TypeElement, FindFieldContainer> map = new HashMap<>();
        parserFindView(env, map);
        parserFindColor(env, map);
        parserFindString(env, map);
        parserFindDrawable(env, map);
        return null;
    }

    private void parserFindDrawable(RoundEnvironment env, Map<TypeElement, FindFieldContainer> map) {
        Set<? extends Element> elements = env.getElementsAnnotatedWith(FindDrawable.class);
        if (elements != null && !elements.isEmpty()) {
            for (Element element : elements) {
                if (validate(element)) {
                    continue;
                }


            }
        }
    }

    private static final String TYPE_ANDROID_DRAWABLE = "android.graphics.drawable.Drawable";

    /**
     * 验证,修饰符必须不是private static,类型必须是Drawable,必须在class里面
     * @param element
     * @return
     */
    private boolean validate(Element element) {
        if (element == null) {
            return false;
        }

        if (!TYPE_ANDROID_DRAWABLE.equals(element.asType().toString())) {
            error(element,"must be Drawable");
            return false;
        }

        Set<Modifier> modifiers = element.getModifiers();
        if (modifiers != null && (modifiers.contains(Modifier.PRIVATE) || modifiers.contains(Modifier.STATIC))) {
            error(element, "modifier must not be private or static");
            return false;
        }

        if (element.getEnclosingElement().getKind() != ElementKind.CLASS) {
            error(element,"must be in class");
            return false;
        }



        return false;
    }


    private void error(Element e,CharSequence msg) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,msg,e);
    }

    private void parserFindString(RoundEnvironment env, Map<TypeElement, FindFieldContainer> map) {

    }

    private void parserFindColor(RoundEnvironment env, Map<TypeElement, FindFieldContainer> map) {

    }

    private void parserFindView(RoundEnvironment env, Map<TypeElement, FindFieldContainer> map) {

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(FindColor.class.getCanonicalName());
        annotations.add(FindDrawable.class.getCanonicalName());
        annotations.add(FindString.class.getCanonicalName());
        annotations.add(FindView.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
