package me.suryaakasam.dao;

import me.suryaakasam.entities.Language;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("languageDAO")
public class LanguageDAO extends AbstractDAO<Language, Integer> { }