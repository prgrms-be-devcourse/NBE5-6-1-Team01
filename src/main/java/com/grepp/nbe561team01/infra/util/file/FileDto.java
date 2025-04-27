package com.grepp.nbe561team01.infra.util.file;

public record FileDto(
    String originFileName,
    String renameFileName,
    String savePath
) {

}