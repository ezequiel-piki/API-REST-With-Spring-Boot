/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.crashcard;

import org.springframework.data.annotation.Id;

/**
 *
 * @author ezequ
 */

public record CrashCard(@Id Long id, Double amount) {
}
