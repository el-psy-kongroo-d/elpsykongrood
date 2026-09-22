package com.elpsykongrood.web;

public record Notice(Type type, String key) {
    public enum Type { SUCCESS, ERROR }
}
