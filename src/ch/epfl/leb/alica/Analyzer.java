/* 
 * Copyright (C) 2017 Laboratory of Experimental Biophysics
 * Ecole Polytechnique Federale de Lausanne
 * 
 * Author: Marcel Stefko
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.epfl.leb.alica;


/**
 * An analyzer receives images from the WorkerThread, processes them, and
 * adjusts its internal state (and output) accordingly. The WorkerThread 
 * can request the output at any time. 
 * @author Marcel Stefko
 */
public interface Analyzer {

    /**
     * Process the next image and adjust internal state.
     * @param image image to be processed
     * @param image_width width in pixels
     * @param image_height height in pixels
     * @param pixel_size_um length of one pixel side in micrometers
     * @param time_ms time of image acquisition in milliseconds
     */
    public void processImage(Object image, int image_width, int image_height, double pixel_size_um, long time_ms);
    
    /**
     * Return output of the analyzer based on current internal state.
     * @return output value of the analyzer to be passed to the controller
     */
    public double getCurrentOutput();
    
    /**
     * 
     * @return unique name of the analyzer.
     */
    public String getName();
}
