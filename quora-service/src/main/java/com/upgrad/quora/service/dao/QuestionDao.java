package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionDao {

    private static final String GETQUESTION_OF_SAME_OWNER = "getquestionOfSameOwner";
    private static final String GET_ALL_QUESTIONS = "getAllQuestions";
    private static final String GET_ALL_QUESTIONS_FOR_USER = "getAllQuestionsForUser";
    private static final String GET_QUESTION = "getQuestion";

    @PersistenceContext
    private EntityManager entityManager;



    public Question createQuestion(Question question) {
        entityManager.persist(question);
        return question;
    }


    public List<Question> getAllQuestionsForUser(String uuId) {
        try {
            return entityManager
                    .createNamedQuery(GET_ALL_QUESTIONS_FOR_USER)
                    .setParameter("uuid", uuId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<Question> getAllQuestions() {
        try {
            return entityManager
                    .createNamedQuery(GET_ALL_QUESTIONS)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


    public Question getQuestion(String questionUuId) {
        try {
            return entityManager
                    .createNamedQuery(GET_QUESTION, Question.class)
                    .setParameter("uuid", questionUuId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public Question editQuestion(Question question) {
        entityManager.persist(question);
        return question;
    }

    public void deleteQuestion(Question question) {
        entityManager.remove(question);
    }
}
