package egd.fmre.bulkemail.dto;

import java.io.Serializable;

public record Mailbox(String name, String address) implements Serializable {
}
