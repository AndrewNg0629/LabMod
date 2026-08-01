package top.aenp.labmod.experiment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import top.aenp.labmod.LabMod;

public class TestRecords {
    public record R1(boolean b1) {
        public static final Codec<R1> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R1::b1)
                ).apply(instance, R1::new)
        );
        public static final R1 DEFAULT = new R1(true);
    }

    public record R2(boolean b1, boolean b2) {
        public static final Codec<R2> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R2::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R2::b2)
                ).apply(instance, R2::new)
        );
        public static final R2 DEFAULT = new R2(false, false);
    }

    public record R3(boolean b1, boolean b2, boolean b3) {
        public static final Codec<R3> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R3::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R3::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R3::b3)
                ).apply(instance, R3::new)
        );
        public static final R3 DEFAULT = new R3(false, false, false);
    }

    public record R4(boolean b1, boolean b2, boolean b3, boolean b4) {
        public static final Codec<R4> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R4::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R4::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R4::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R4::b4)
                ).apply(instance, R4::new)
        );
        public static final R4 DEFAULT = new R4(false, false, false, false);
    }

    public record R5(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5) {
        public static final Codec<R5> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R5::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R5::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R5::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R5::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R5::b5)
                ).apply(instance, R5::new)
        );
        public static final R5 DEFAULT = new R5(false, false, false, false, false);
    }

    public record R6(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6) {
        public static final Codec<R6> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R6::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R6::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R6::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R6::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R6::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R6::b6)
                ).apply(instance, R6::new)
        );
        public static final R6 DEFAULT = new R6(false, false, false, false, false, false);
    }

    public record R7(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7) {
        public static final Codec<R7> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R7::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R7::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R7::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R7::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R7::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R7::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R7::b7)
                ).apply(instance, R7::new)
        );
        public static final R7 DEFAULT = new R7(false, false, false, false, false, false, false);
    }

    public record R8(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8) {
        public static final Codec<R8> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R8::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R8::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R8::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R8::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R8::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R8::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R8::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R8::b8)
                ).apply(instance, R8::new)
        );
        public static final R8 DEFAULT = new R8(false, false, false, false, false, false, false, false);
    }

    public record R9(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9) {
        public static final Codec<R9> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R9::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R9::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R9::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R9::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R9::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R9::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R9::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R9::b8),
                        Codec.BOOL.fieldOf("b9").forGetter(R9::b9)
                ).apply(instance, R9::new)
        );
        public static final R9 DEFAULT = new R9(false, false, false, false, false, false, false, false, false);
    }

    public record R10(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10) {
        public static final Codec<R10> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R10::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R10::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R10::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R10::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R10::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R10::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R10::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R10::b8),
                        Codec.BOOL.fieldOf("b9").forGetter(R10::b9),
                        Codec.BOOL.fieldOf("b10").forGetter(R10::b10)
                ).apply(instance, R10::new)
        );
        public static final R10 DEFAULT = new R10(false, false, false, false, false, false, false, false, false, false);
    }

    public record R11(
            boolean b1, boolean b2, boolean b3, boolean b4, boolean b5,
            boolean b6, boolean b7, boolean b8, boolean b9, boolean b10,
            boolean b11
    ) {
        public static final Codec<R11> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R11::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R11::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R11::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R11::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R11::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R11::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R11::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R11::b8),
                        Codec.BOOL.fieldOf("b9").forGetter(R11::b9),
                        Codec.BOOL.fieldOf("b10").forGetter(R11::b10),
                        Codec.BOOL.fieldOf("b11").forGetter(R11::b11)
                ).apply(instance, R11::new)
        );
        public static final R11 DEFAULT = new R11(false, false, false, false, false, false, false, false, false, false, false);
    }

    public record R12(
            boolean b1, boolean b2, boolean b3, boolean b4, boolean b5,
            boolean b6, boolean b7, boolean b8, boolean b9, boolean b10,
            boolean b11, boolean b12
    ) {
        public static final Codec<R12> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R12::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R12::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R12::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R12::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R12::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R12::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R12::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R12::b8),
                        Codec.BOOL.fieldOf("b9").forGetter(R12::b9),
                        Codec.BOOL.fieldOf("b10").forGetter(R12::b10),
                        Codec.BOOL.fieldOf("b11").forGetter(R12::b11),
                        Codec.BOOL.fieldOf("b12").forGetter(R12::b12)
                ).apply(instance, R12::new)
        );
        public static final R12 DEFAULT = new R12(false, false, false, false, false, false, false, false, false, false, false, false);
    }

    public record R13(
            boolean b1, boolean b2, boolean b3, boolean b4, boolean b5,
            boolean b6, boolean b7, boolean b8, boolean b9, boolean b10,
            boolean b11, boolean b12, boolean b13
    ) {
        public static final Codec<R13> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R13::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R13::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R13::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R13::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R13::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R13::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R13::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R13::b8),
                        Codec.BOOL.fieldOf("b9").forGetter(R13::b9),
                        Codec.BOOL.fieldOf("b10").forGetter(R13::b10),
                        Codec.BOOL.fieldOf("b11").forGetter(R13::b11),
                        Codec.BOOL.fieldOf("b12").forGetter(R13::b12),
                        Codec.BOOL.fieldOf("b13").forGetter(R13::b13)
                ).apply(instance, R13::new)
        );
        public static final R13 DEFAULT = new R13(false, false, false, false, false, false, false, false, false, false, false, false, false);
    }

    public record R14(
            boolean b1, boolean b2, boolean b3, boolean b4, boolean b5,
            boolean b6, boolean b7, boolean b8, boolean b9, boolean b10,
            boolean b11, boolean b12, boolean b13, boolean b14
    ) {
        public static final Codec<R14> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R14::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R14::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R14::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R14::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R14::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R14::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R14::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R14::b8),
                        Codec.BOOL.fieldOf("b9").forGetter(R14::b9),
                        Codec.BOOL.fieldOf("b10").forGetter(R14::b10),
                        Codec.BOOL.fieldOf("b11").forGetter(R14::b11),
                        Codec.BOOL.fieldOf("b12").forGetter(R14::b12),
                        Codec.BOOL.fieldOf("b13").forGetter(R14::b13),
                        Codec.BOOL.fieldOf("b14").forGetter(R14::b14)
                ).apply(instance, R14::new)
        );
        public static final R14 DEFAULT = new R14(false, false, false, false, false, false, false, false, false, false, false, false, false, false);
    }

    public record R15(
            boolean b1, boolean b2, boolean b3, boolean b4, boolean b5,
            boolean b6, boolean b7, boolean b8, boolean b9, boolean b10,
            boolean b11, boolean b12, boolean b13, boolean b14, boolean b15
    ) {
        public static final Codec<R15> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R15::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R15::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R15::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R15::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R15::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R15::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R15::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R15::b8),
                        Codec.BOOL.fieldOf("b9").forGetter(R15::b9),
                        Codec.BOOL.fieldOf("b10").forGetter(R15::b10),
                        Codec.BOOL.fieldOf("b11").forGetter(R15::b11),
                        Codec.BOOL.fieldOf("b12").forGetter(R15::b12),
                        Codec.BOOL.fieldOf("b13").forGetter(R15::b13),
                        Codec.BOOL.fieldOf("b14").forGetter(R15::b14),
                        Codec.BOOL.fieldOf("b15").forGetter(R15::b15)
                ).apply(instance, R15::new)
        );
        public static final R15 DEFAULT = new R15(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
    }

    public record R16(
            boolean b1, boolean b2, boolean b3, boolean b4,
            boolean b5, boolean b6, boolean b7, boolean b8,
            boolean b9, boolean b10, boolean b11, boolean b12,
            boolean b13, boolean b14, boolean b15, boolean b16
    ) {
        public static final Codec<R16> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.BOOL.fieldOf("b1").forGetter(R16::b1),
                        Codec.BOOL.fieldOf("b2").forGetter(R16::b2),
                        Codec.BOOL.fieldOf("b3").forGetter(R16::b3),
                        Codec.BOOL.fieldOf("b4").forGetter(R16::b4),
                        Codec.BOOL.fieldOf("b5").forGetter(R16::b5),
                        Codec.BOOL.fieldOf("b6").forGetter(R16::b6),
                        Codec.BOOL.fieldOf("b7").forGetter(R16::b7),
                        Codec.BOOL.fieldOf("b8").forGetter(R16::b8),
                        Codec.BOOL.fieldOf("b9").forGetter(R16::b9),
                        Codec.BOOL.fieldOf("b10").forGetter(R16::b10),
                        Codec.BOOL.fieldOf("b11").forGetter(R16::b11),
                        Codec.BOOL.fieldOf("b12").forGetter(R16::b12),
                        Codec.BOOL.fieldOf("b13").forGetter(R16::b13),
                        Codec.BOOL.fieldOf("b14").forGetter(R16::b14),
                        Codec.BOOL.fieldOf("b15").forGetter(R16::b15),
                        Codec.BOOL.fieldOf("b16").forGetter(R16::b16)
                ).apply(instance, R16::new)
        );
        public static final R16 DEFAULT = new R16(
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false
        );
    }

    public static void test() {
        LabMod.LOGGER.info(R1.CODEC.encodeStart(JsonOps.INSTANCE, R1.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R2.CODEC.encodeStart(JsonOps.INSTANCE, R2.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R3.CODEC.encodeStart(JsonOps.INSTANCE, R3.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R4.CODEC.encodeStart(JsonOps.INSTANCE, R4.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R5.CODEC.encodeStart(JsonOps.INSTANCE, R5.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R6.CODEC.encodeStart(JsonOps.INSTANCE, R6.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R7.CODEC.encodeStart(JsonOps.INSTANCE, R7.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R8.CODEC.encodeStart(JsonOps.INSTANCE, R8.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R9.CODEC.encodeStart(JsonOps.INSTANCE, R9.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R10.CODEC.encodeStart(JsonOps.INSTANCE, R10.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R11.CODEC.encodeStart(JsonOps.INSTANCE, R11.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R12.CODEC.encodeStart(JsonOps.INSTANCE, R12.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R13.CODEC.encodeStart(JsonOps.INSTANCE, R13.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R14.CODEC.encodeStart(JsonOps.INSTANCE, R14.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R15.CODEC.encodeStart(JsonOps.INSTANCE, R15.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
        LabMod.LOGGER.info(R16.CODEC.encodeStart(JsonOps.INSTANCE, R16.DEFAULT).getOrThrow().getAsJsonObject().keySet().toString());
    }
}


