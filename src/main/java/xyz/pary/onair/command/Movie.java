package xyz.pary.onair.command;

import java.util.Objects;
import xyz.pary.onair.OnAirParserException;
import xyz.pary.onair.command.parameter.Duration;
import xyz.pary.onair.command.parameter.Fade;
import xyz.pary.onair.command.parameter.IFade;
import xyz.pary.onair.command.parameter.IMarkIn;
import xyz.pary.onair.command.parameter.MarkIn;
import xyz.pary.onair.command.parameter.util.ParameterParser;

public class Movie extends Command implements IMarkIn, IFade {

    private static final String DEFAULT_DURATION = "0:00:00.01";

    private final MarkIn markIn;
    private final Duration duration;
    private final Fade fadeOut;
    private final String fileName;

    /**
     *
     * @param command команда в виде строки раписания
     */
    public Movie(String command) {
        super(CommandKey.MOVIE);
        this.markIn = ParameterParser.getMarkIn(command);
        Duration duration = ParameterParser.getDuration(command);
        if (duration != null) {
            this.duration = duration;
        } else {
            this.duration = new Duration(DEFAULT_DURATION);
        }
        this.fadeOut = ParameterParser.getFade(command);
//        if (this.fadeOut == null) {
//            throw new RuntimeException("Отсутствует время исчезновения");
//        }
        this.fileName = ParameterParser.getFileName(command);
        if (this.fileName == null) {
            throw new OnAirParserException("Отсутствует имя файла");
        }
    }

    /**
     *
     * @param markIn MarkIn
     * @param duration длительность
     * @param fadeOut фейд
     * @param fileName название файла
     */
    public Movie(MarkIn markIn, Duration duration, Fade fadeOut, String fileName) {
        super(CommandKey.MOVIE);
        if (duration == null) {
            duration = new Duration(DEFAULT_DURATION);
        }
        if (fileName == null) {
            throw new OnAirParserException("Отсутствует имя файла");
        }
        this.markIn = markIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
        this.fileName = fileName;
    }

    /**
     *
     * @return название файла
     */
    public String getFileName() {
        return fileName;
    }

    @Override
    public MarkIn getMarkIn() {
        return markIn;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public Fade getFade() {
        return fadeOut;
    }

    @Override
    public String getName() {
        int i = fileName.lastIndexOf("\\");
        if (i > -1) {
            return fileName.substring(i + 1);
        }
        return fileName;
    }

    @Override
    public String toSheduleRow() {
        //movie <0:03:01.60> 0:13:30.92 [0.12] D:\Movies\dolphinarium.avi
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        if (markIn != null) {
            sb.append(markIn.toString()).append(" ");
        }
        sb.append(duration.toString()).append(" ");
        if (fadeOut != null) {
            sb.append(fadeOut.toString()).append(" ");
        }
        sb.append(fileName);
        return sb.toString();
    }

//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 43 * hash + Objects.hashCode(this.markIn);
//        hash = 43 * hash + Objects.hashCode(this.duration);
//        hash = 43 * hash + Objects.hashCode(this.fadeOut);
//        hash = 43 * hash + Objects.hashCode(this.fileName);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Movie other = (Movie) obj;
//        if (!Objects.equals(this.fileName, other.fileName)) {
//            return false;
//        }
//        if (!Objects.equals(this.markIn, other.markIn)) {
//            return false;
//        }
//        if (!Objects.equals(this.duration, other.duration)) {
//            return false;
//        }
//        if (!Objects.equals(this.fadeOut, other.fadeOut)) {
//            return false;
//        }
//        return true;
//    } 
}
