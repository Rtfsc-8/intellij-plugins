package com.intellij.tapestry.intellij.lang.descriptor;

import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlDocument;
import com.intellij.psi.xml.XmlElement;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.tapestry.core.TapestryProject;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlNSDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Fedor Korotkov
 */
public class TapestryParametersNamespaceDescriptor implements XmlNSDescriptor {
  private XmlFile myFile;
  private XmlElement myElement;

  @Override
  public XmlElementDescriptor getElementDescriptor(@NotNull XmlTag tag) {
    return null;
  }

  @Override
  @NotNull
  public XmlElementDescriptor[] getRootElementsDescriptors(@Nullable XmlDocument doc) {
    if (doc == null) return XmlElementDescriptor.EMPTY_ARRAY;
    XmlTag rootTag = doc.getRootTag();
    if (rootTag == null) return XmlElementDescriptor.EMPTY_ARRAY;
    TapestryNamespaceDescriptor tapestryNamespaceDescriptor = TapestryXmlExtension.getTapestryTemplateDescriptor(rootTag);
    if (tapestryNamespaceDescriptor == null) return XmlElementDescriptor.EMPTY_ARRAY;
    return DescriptorUtil.getTmlSubelementDescriptors(rootTag, tapestryNamespaceDescriptor);
  }

  @Override
  @Nullable
  public XmlFile getDescriptorFile() {
    return myFile;
  }

  @Override
  public PsiElement getDeclaration() {
    return myElement;
  }

  @Override
  public String getName(PsiElement context) {
    return getName();
  }

  @Override
  public String getName() {
    return myFile.getName();
  }

  @Override
  public void init(PsiElement element) {
    myFile = (XmlFile)element.getContainingFile();
    myElement = (XmlElement)element;

    if (element instanceof XmlDocument) {
      myElement = ((XmlDocument)element).getRootTag();
    }
  }

  @Override
  @NotNull
  public Object[] getDependencies() {
    return TapestryProject.JAVA_STRUCTURE_DEPENDENCY;
  }
}
