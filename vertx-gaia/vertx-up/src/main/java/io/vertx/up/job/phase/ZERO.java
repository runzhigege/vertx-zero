package io.vertx.up.job.phase;

import io.vertx.up.job.plugin.JobIncome;
import io.vertx.up.job.plugin.JobOutcome;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {
    ConcurrentMap<String, JobIncome> INCOMES = new ConcurrentHashMap<>();
    ConcurrentMap<String, JobOutcome> OUTCOMES = new ConcurrentHashMap<>();
    ConcurrentMap<String, Phase> PHASES = new ConcurrentHashMap<>();
}